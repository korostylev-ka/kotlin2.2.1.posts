package attachments

import Attachment

// класс, реализующий интерфейс для вложений граффити
open class  GraffitiAttachment(
    override val type: String = "Graffiti",
): Attachment

//класс граффити
class Graffiti(
    val id: Int,
    val ownerId: Int,
    val url: String,
    val width: Int,
    val height: Int,
    override val type: String = "Graffiti"
): FileAttachment()