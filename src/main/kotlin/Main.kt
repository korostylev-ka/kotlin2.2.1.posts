import java.util.*

//Дата-класс для постов
data class Post(
    val id: Int, //Идентификатор записи.
    val ownerId: Int, //Идентификатор владельца стены, на которой размещена запись
    val fromId: Int, //Идентификатор автора записи (от чьего имени опубликована запись).
    val createdBy: Int, //Идентификатор администратора, который опубликовал запись.
    val date: Int, //Время публикации записи
    val text: String?, //Текст записи.
    val replyOwnerId: Int, //Идентификатор владельца записи, в ответ на которую была оставлена текущая
    val replyPostId: Int, //Идентификатор записи, в ответ на которую была оставлена текущая
    val friendsOnly: Boolean, // если запись была создана с опцией «Только для друзей».
    val comments: Comments, //Информация о комментариях к записи, объект с полями:
    val copyright: Copyright, //Источник материала, объект с полями:
    var likes: Likes,    //Информация о лайках к записи, объект с полями:
    var reposts: Reposts,    //Информация о репостах записи («Рассказать друзьям»), объект с полями:
    var views: Views,    //Информация о просмотрах записи. Объект с единственным полем:
    val postType: String, //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val postSource: PostSource, //Информация о способе размещения записи
    val geo: Geo?, //Информация о местоположении
    val signerId: Int,   //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем
    val canPin: Boolean, //Информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean, //Информация о том, может ли текущий пользователь удалить запись
    val canEdit: Boolean, //Информация о том, может ли текущий пользователь редактировать запись
    val isPinned: Boolean,   //Информация о том, что запись закреплена
    val markedAsAds: Boolean, //Информация о том, содержит ли запись отметку "реклама"
    val isFavorite: Boolean, //если объект добавлен в закладки у текущего пользователя
    val donut: Donut //Информация о записи VK Donut
)

//Информация о комментариях к записи, объект с полями:
data class Comments(
    val count: Int, //количество комментариев
    val canPost: Boolean,  // информация о том, может ли текущий пользователь комментировать запись
    val groupsCanPost: Boolean,   // информация о том, могут ли сообщества комментировать запись
    val canClose: Boolean,  //может ли текущий пользователь закрыть комментарии к записи
    val canOpen: Boolean   //может ли текущий пользователь открыть комментарии к записи
)

//Источник материала, объект с полями:
data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

//Информация о лайках к записи, объект с полями:
data class Likes(
    val count: Int, //число пользователей, которым понравилась запись
    val userLikes: Boolean,    // наличие отметки «Мне нравится» от текущего пользователя
    val canLike: Boolean,  //информация о том, может ли текущий пользователь поставить отметку «Мне нравится»
    val canPublish: Boolean // информация о том, может ли текущий пользователь сделать репост записи
)

//Информация о репостах записи («Рассказать друзьям»), объект с полями:
data class Reposts(
    val count: Int, //число пользователей, скопировавших запись;
    val userReposted: Boolean   //наличие репоста от текущего пользователя
)

//Информация о просмотрах записи. Объект с единственным полем:
data class Views(
    val count: Int //число просмотров записи
)

data class PostSource(
    val type: String, //Тип источника.
    val platform: String, //Название платформы, если оно доступно.
    val data: String, //Тип действия (только для type = vk или widget).
    val url: String //URL ресурса, с которого была опубликована запись.
)

//Информация о местоположении
data class Geo(
    val type: String, // тип места
    val coordinates: String,// координаты места
    val place: Place // описание места (если оно добавлено).
)

// описание места (если оно добавлено).
data class Place(
    val id: Int, //Идентификатор места.
    val title: String, //Название места
    val latitude: Int, //Географическая широта, заданная в градусах (от -90 до 90)
    val longitude: Int, //Географическая широта, заданная в градусах (от -90 до 90)
    val created: Int, //Дата создания места в Unixtime
    val icon: String, //Иконка места, URL изображения
    val checkins: Int, //Число отметок в этом месте
    val updated: Int, //Дата обновления места в Unixtime
    val type: Int, //Тип места
    val country: Int, //Идентификатор страны
    val city: Int, //Идентификатор города
    val address: String //Адрес места
)

//Информация о записи VK Donut
data class Donut(
    val isDonut: Boolean,   //запись доступна только платным подписчикам VK Donut
    val paidDuration: Int,  // время, в течение которого запись будет доступна только платным подписчикам VK Donut
    val canPublish_free_copy: Boolean,  //можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: String    //информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
)

object WallService {
    private var posts = emptyArray<Post>()  //массив с постами
    private var origId = 0 //уникальный id

    //метод очистки массива
    fun erasePosts(): Unit {
        posts = emptyArray()
    }

    //метод возвращения текущего значения оригинального id
    fun getOriginId(): Int {
        return origId
    }

    //сеттер начального id
    fun setOriginId(value: Int): Int {
        origId = value
        return origId
    }

    //метод добавления поста в массив
    fun add(post: Post): Post {
        //поле для проверки поста на повтор
        var isOriginId = true
        //добавляем первый пост
        if (posts.size == 0) {
            posts += post.copy(id = ++origId)
        } else {
            for (postValue in posts) {
                //проверка на оригинальность по id поста
                if (post.id == postValue.id) isOriginId = false
            }
            //если пост оригинальный, добавляем в массив и изменяем уникальный id
            if (isOriginId == true) {
                posts += post.copy(id = ++origId)
            }
        }
        return posts.last()
    }

    //метод обновления записи
    fun update(post: Post): Boolean {
        //поле для проверки повтора id
        var isUpdate = false
        for ((index, postCheck) in posts.withIndex()) {
            //проверка на оригинальность по id поста
            if (postCheck.id == post.id) {
                posts[index] = post.copy(fromId = postCheck.fromId, date = postCheck.date)
                isUpdate = true
            }
        }
        return isUpdate
    }
}

fun main() {
    var postOne = Post(
        1,
        1111,
        1111,
        1111,
        1656697687,
        "message1",
        1110,
        2,
        false,
        Comments(25, true, true, true, true),
        Copyright(11, "www.netology.ru", "data class", "Post"),
        Likes(15, true, true, true),
        Reposts(15, true),
        Views(1000),
        "post",
        PostSource("vk", "android", "profile_activity", "netology.ru"),
        Geo("Горы", "27°59′17″ с. ш. 86°55′31",
            Place(1,"Эверест",275917,865531,1641025298,"https://manrule.ru/images/article/orig/2020/12/vse-o-voshozhdenii-na-everest-7.jpg",
                10,1641025298,1,977,2,"27°59′17″ с. ш. 86°55′31″")),
        1111,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false, 0, false, "")

    )

    var postTwo = Post(
        2,
        1112,
        1112,
        1112,
        1656697687,
        "message2",
        1111,
        1,
        false,
        Comments(29, true, true, true, true),
        Copyright(12, "www.netology.ru", "Services", "Post"),
        Likes(35, true, true, true),
        Reposts(14, true),
        Views(1200),
        "post",
        PostSource("vk", "android", "profile_activity", "netology.ru"),
        Geo("Горы", "27°59′17″ с. ш. 86°55′31",
            Place(1,"Эверест",275917,865531,1641025298,"https://manrule.ru/images/article/orig/2020/12/vse-o-voshozhdenii-na-everest-7.jpg",
                10,1641025298,1,977,2,"27°59′17″ с. ш. 86°55′31″")),
        1112,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false, 0, false, "")
    )

    var postThree = Post(
        3,
        1113,
        1113,
        1113,
        1656771927,
        "message3",
        1112,
        2,
        true,
        Comments(29, true, true, true, true),
        Copyright(12, "www.netology.ru", "Services", "Post"),
        Likes(37, true, true, true),
        Reposts(104, true),
        Views(2300),
        "post",
        PostSource("vk", "android", "profile_activity", "netology.ru"),
        Geo("Горы", "27°59′17″ с. ш. 86°55′31",
            Place(1,"Эверест",275917,865531,1641025298,"https://manrule.ru/images/article/orig/2020/12/vse-o-voshozhdenii-na-everest-7.jpg",
                10,1641025298,1,977,2,"27°59′17″ с. ш. 86°55′31″")),
        1112,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false, 0, false, "")
    )

    var postOneUpdate = Post(
        1,
        2222,
        2221,
        2222,
        1656771927,
        "message1",
        1110,
        2,
        false,
        Comments(45, true, true, true, true),
        Copyright(11, "www.netology.ru", "data class", "Post"),
        Likes(145, true, true, true),
        Reposts(25, true),
        Views(700),
        "post",
        PostSource("vk", "android", "profile_activity", "netology.ru"),
        Geo("Горы", "27°59′17″ с. ш. 86°55′31",
            Place(1,"Эверест",275917,865531,1641025298,"https://manrule.ru/images/article/orig/2020/12/vse-o-voshozhdenii-na-everest-7.jpg",
                10,1641025298,1,977,2,"27°59′17″ с. ш. 86°55′31″")),
        1111,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false, 0, false, "")
    )
    WallService.add(postOne)
    WallService.add(postTwo)
    //проверяем, что повторно пост с имеющимся id не добавится
    WallService.add(postTwo)
}


