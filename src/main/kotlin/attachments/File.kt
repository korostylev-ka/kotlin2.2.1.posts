package attachments

import Attachment

// класс, реализующий интерфейс для вложений файлов
open class  FileAttachment(
    override val type: String = "File",
): Attachment

//класс файлов
class File(
    val id: Int, //Идентификатор файла
    val ownerId: Int, //Идентификатор пользователя, загрузившего файл
    val title: String, //Название файла
    val size: Int,  //Размер файла в байтах
    val ext: String, //Расширение файла
    val url: String, //Адрес файла, по которому его можно загрузить.
    val date: Int, //Дата добавления в формате Unixtime
    val types: Int, //Тип файла.
    val preview: Preview, //Информация для предварительного просмотра файла
    override val type: String = "File"
): FileAttachment()

//Информация для предварительного просмотра файла
data class Preview(
    val photo: Photos, //изображения для предпросмотра
    val graffitis: Graffitis, //данные о граффити
    val audioMessage: AudioMessage //данные об аудиосообщении
)

//Изображения для предпросмотра
data class Photos(
    val sizes: Array<Int> //массив копий изображения в разных размерах
)

//Данные о граффити
data class Graffitis(
    val src: String, // URL файла с граффити
    val width: Int, //ширина изображения в px
    val height: Int //высота изображения в px
)

//Данные об аудиосообщении
data class  AudioMessage(
    val duration: Int, // длительность аудиосообщения в секундах
    val waveform: Array<Int>, //массив значений (integer) для визуального отображения звука
    val linkOgg: String, //URL .ogg-файла
    val linkMp3: String //URL .mp3-файла
)