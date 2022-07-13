package attachments

import Attachment

// класс, реализующий интерфейс для вложений аудио
open class  AudioAttachment(
    override val type: String = "Audio",
): Attachment

//класс аудио
class Audio(
    val id: Int, //Идентификатор аудиозаписи
    val ownerId: Int, //Идентификатор владельца аудиозаписи
    val artist: String, //Исполнитель
    val title: String, //Название композиции
    val duration: Int, //Длительность аудиозаписи в секундах
    val url: String, //Ссылка на mp3
    val lyricsId: Int?, //Идентификатор текста аудиозаписи (если доступно)
    val albumId: Int?, //Идентификатор альбома, в котором находится аудиозапись (если присвоен)
    val genreId: Int, //Идентификатор жанра из списка аудио жанров
    val date: Int, //Дата добавления
    val noSearch: Boolean, //true, если включена опция «Не выводить при поиске». Если опция отключена, поле не возвращается
    val isHq: Boolean, //true, если аудио в высоком качестве
    override val type: String = "Audio"
): AudioAttachment()
