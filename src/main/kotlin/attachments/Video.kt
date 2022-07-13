package attachments

import Attachment

// класс, реализующий интерфейс для вложений видео
open class  VideoAttachment(
    override val type: String = "Video",
): Attachment

//.класс видео
class Video(
    val id: Int, //Идентификатор видеозаписи
    val ownerId: Int, //Идентификатор владельца видеозаписи
    val title: String, //Название видеозаписи
    val description: String, //Текст описания видеозаписи
    val duration: Int, //Длительность ролика в секундах
    val image: Array<Image>, //Изображение обложки. Содержит массив объектов с полями
    val firstFrame: Array<firstFrame>, //Изображение первого кадра. Содержит массив объектов с полями
    val date: Int, //Дата создания видеозаписи в формате Unixtime
    val addingDate: Int, //Дата добавления видеозаписи пользователем или группой в формате Unixtime
    val views: Int, //Количество просмотров видеозаписи
    val localViews: Int, //Если видео внешнее, количество просмотров ВКонтакте
    val comments: Int?, //Количество комментариев к видеозаписи. Поле не возвращается, если комментарии недоступны.
    val player: String, //URL страницы с плеером, который можно использовать для воспроизведения ролика в браузере.
    val platform: String, //Название платформы (для видеозаписей, добавленных с внешних сайтов)
    val canAdd: Boolean, //Может ли пользователь добавить видеозапись к себе
    val isPrivate: Boolean, //Поле возвращается, если видеозапись приватная
    val access_Key: String, //Ключ доступа к объекту.
    val processing: Int, //Поле возвращается в том случае, если видеоролик находится в процессе обработки, всегда содержит 1
    val isFavorite: Boolean, //true, если объект добавлен в закладки у текущего пользователя
    val canComment: Boolean, //Может ли пользователь комментировать видео
    val canEdit: Boolean, //Может ли пользователь редактировать видео
    val canLike: Boolean, //Может ли пользователь добавить видео в список <<Мне нравится>>
    val canRepost: Boolean, //Может ли пользователь сделать репост видео
    val canSubscribe: Boolean, //Может ли пользователь подписаться на автора видео
    val canAddToFaves: Boolean, //Может ли пользователь добавить видео в избранное
    val canAttachLink: Boolean, //Может ли пользователь прикрепить кнопку действия к видео
    val width: Int, //Ширина видео
    val height: Int, //Высота видео
    val userId: Int, //Идентификатор пользователя, загрузившего видео, если оно было загружено в группу одним из участников
    val converting: Boolean, //Конвертируется ли видео
    val added: Int, //Добавлено ли видео в альбомы пользователя
    val isSubscribed: Boolean, //Подписан ли пользователь на автора видео
    val repeat: Int, //Поле возвращается в том случае, если видео зациклено, всегда содержит 1
    val types: String, //Тип видеозаписи.
    val balance: Int, //Баланс донатов в прямой трансляции
    val liveStatus: String, //Статус прямой трансляции. Может принимать значения: waiting, started, finished, failed, upcoming
    val live: Boolean, //Поле возвращается в том случае, если видеозапись является прямой трансляцией
    val upcoming: Int, //Поле свидетельствует о том, что трансляция скоро начнётся. Для live =1
    val spectators: Int, //Количество зрителей прямой трансляции
    val likes: LikesVideo, //Содержит объект отметки «Мне нравится».
    val reposts: RepostsVideo, //Содержит объект репоста.
    override val type: String = "Video"
): VideoAttachment()

//Содержит объект репоста для видео вложений
data class RepostsVideo(
    val count: Int, //счетчик общего количества репостов. Содержит сумму репостов на стену и в личные сообщения
    val userReposted: Boolean,   //информация о том, сделал ли текущий пользователь репост этого видео.
    val wallCount: Int, //счетчик репостов на стену
    val mailCount: Int //счетчик репостов в личные сообщения
)

//Содержит объект отметки «Мне нравится» для Видео вложений
data class LikesVideo(
    val count: Int, //количество лайков
    val userLikes: Boolean,    // добавлено ли видео в список «Мне нравится» текущего пользователя.
)

//Изображение обложки
data class Image(
    val height: Int, //высота изображения
    val url: String, //ссылка на изображение.
    val width: Int, //ширина изображения
    val withPadding: Int, //поле возвращается, если изображение с отбивкой, всегда содержит 1
)

//Изображение первого кадра
data class firstFrame(
    val height: Int,
    val width: Int,
    val url:String
)
