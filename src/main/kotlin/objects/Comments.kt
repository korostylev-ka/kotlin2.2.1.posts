package objects

import Attachment

//класс для комментариев
data class Comments(
    val id: Int, //Идентификатор комментария
    val fromId: Int, //Идентификатор автора комментария
    val date: Int, //Дата создания комментария в формате Unixtime
    val text: String, //Текст комментария
    val donat: Donat, //Информация о VK Donut.
    val replyToUser: Int, //Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий
    val replyToComment: Int, //Идентификатор комментария, в ответ на который оставлен текущий
    val attachment: Attachment?, //Медиавложения комментария (фотографии, ссылки и т.п.)
    val thread: Thread //Информация о вложенной ветке комментариев
)

//Информация о VK Donut
data class Donat(
    val isDon: Boolean, // является ли комментатор подписчиком VK Donut
    val placeholder: String //заглушка для пользователей, которые не оформили подписку VK Donut
)

//Информация о вложенной ветке комментариев
data class Thread(
    val count: Int, // количество комментариев в ветке
    val canPost: Boolean, //может ли текущий пользователь оставлять комментарии в этой ветке
    val showReplyButton: Boolean, // нужно ли отображать кнопку «ответить» в ветке
    val groupsCanPost: Boolean // могут ли сообщества оставлять комментарии в ветке
)
