import java.util.*

data class Post(
   val id: Int, //Идентификатор записи.
   val ownerId: Int, //Идентификатор владельца стены, на которой размещена запись
   val fromId: Int, //Идентификатор автора записи (от чьего имени опубликована запись).
   val createdBy: Int, //Идентификатор администратора, который опубликовал запись.
   val date: Int, //Время публикации записи
   val text: String, //Текст записи.
   val replyOwnerId: Int, //Идентификатор владельца записи, в ответ на которую была оставлена текущая
   val replyPostId: Int, //Идентификатор записи, в ответ на которую была оставлена текущая
   val friendsOnly: Boolean, // если запись была создана с опцией «Только для друзей».
   val comments: Comments, //Информация о комментариях к записи, объект с полями:
   val copyright: Copyright, //Источник материала, объект с полями:
   var likes: Likes,    //Информация о лайках к записи, объект с полями:
   var reposts: Reposts,    //Информация о репостах записи («Рассказать друзьям»), объект с полями:
   var views: Views,    //Информация о просмотрах записи. Объект с единственным полем:
   val postType: String, //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
   val signerId: Int,   //Идентификатор автора, если запись была опубликована от имени сообщества и подписана пользователем
   val canPin: Boolean, //Информация о том, может ли текущий пользователь закрепить запись
   val canDelete: Boolean, //Информация о том, может ли текущий пользователь удалить запись
   val canEdit: Boolean, //Информация о том, может ли текущий пользователь редактировать запись
   val isPinned: Boolean,   //Информация о том, что запись закреплена
   val markedAsAds: Boolean, //Информация о том, содержит ли запись отметку "реклама"
   val isFavorite: Boolean, //если объект добавлен в закладки у текущего пользователя
   val donut: Donut //Информация о записи VK Donut

) {
    /*override fun toString(): String {
        val postPrint = ("id поста: $id , текст поста: $text")
        return postPrint
    }*/
}

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

//Информация о записи VK Donut
data class Donut(
    val isDonut: Boolean,   //запись доступна только платным подписчикам VK Donut
    val paidDuration: Int,  // время, в течение которого запись будет доступна только платным подписчикам VK Donut
    val canPublish_free_copy: Boolean,  //можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: String    //информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:

)

object WallService {

    private var posts = emptyArray<Post>()  //массив с постами
    private var isOriginId = true //поле для проверки поста на повтор
    private var origId = 0 //уникальный id
    private var isUpdate = false

    //метод очистки массива
    fun erasePosts(): Unit {
        posts = emptyArray()
    }

    fun getOriginId(): Int {
        return origId
    }
    fun add(post: Post): Post {
        //добавляем первый пост
        if (posts.size == 0) {
            posts += post
            origId += 1
        } else {
            for (post2 in posts) {
                //проверка на оригинальность по id поста
                if (post.id == post2.id) isOriginId = false

            }
            //если пост оригинальный, добавляем в массив и изменяем уникальный id
            if (isOriginId == true) {
                posts += post
                origId += 1
            }
        }
        return post
    }

    //Обновление записи
    fun update(post: Post): Boolean{
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
        Comments(25,true,true,true,true),
        Copyright(11,"www.netology.ru","data class","Post"),
        Likes(15,true,true,true),
        Reposts(15,true),
        Views(1000),
        "post",
        1111,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false,0,false,""))

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
        Comments(29,true,true,true,true),
        Copyright(12,"www.netology.ru","Services","Post"),
        Likes(35,true,true,true),
        Reposts(14,true),
        Views(1200),
        "post",
        1112,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false,0,false,""))

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
        Comments(29,true,true,true,true),
        Copyright(12,"www.netology.ru","Services","Post"),
        Likes(37,true,true,true),
        Reposts(104,true),
        Views(2300),
        "post",
        1112,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false,0,false,""))

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
        Comments(45,true,true,true,true),
        Copyright(11,"www.netology.ru","data class","Post"),
        Likes(145,true,true,true),
        Reposts(25,true),
        Views(700),
        "post",
        1111,
        true,
        true,
        true,
        false,
        false,
        true,
        Donut(false,0,false,""))

    WallService.add(postOne)
    WallService.add(postTwo)
    //проверяем, что повторно пост с имеющимся id не добавится
    WallService.add(postTwo)

}


