package attachments

import Attachment

// класс, реализующий интерфейс  для вложений фото
open class  PhotoAttachment(
    override val type: String = "Photo",
): Attachment

//класс фото
class Photo(
    val id: Int, //Идентификатор фотографии
    val albumId: Int, //Идентификатор альбома, в котором находится фотография
    val ownerId: Int, //Идентификатор владельца фотографии
    val userId: Int, //Идентификатор пользователя, загрузившего фото
    val text: String, //Текст описания фотографии
    val date: Int, //Дата добавления в формате Unixtime,
    val sizes: Array<Copys>, //Массив с копиями изображения в разных размерах
    override val type: String = "Photo"
): PhotoAttachment()

data class Copys(
    val typeCopy: String, //тип копии
    val url: String, //URL копии
    val width: Int, //высота в px
    val height: Int, //ширина в px
)