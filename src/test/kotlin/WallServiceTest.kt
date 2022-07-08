import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        WallService.erasePosts()
        WallService.setOriginId(0)
        val startId = WallService.getOriginId()
        //проверяем начальное значение id
        assertEquals(0,startId)
        val  postAdd = WallService.add(Post(
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
            Donut(false,0,false,"")))
        val nextId = WallService.getOriginId()
        //проверяем id после добавления поста
        assertEquals(1,nextId)
    }

    @Test
    fun update() {
        //очищаем массив
        WallService.erasePosts()
        WallService.setOriginId(0)
        val  postOne = WallService.add(Post(
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
            Donut(false,0,false,"")))

        val  postTwo = WallService.add(Post(
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
            Donut(false,0,false,"")))

        val postOneUpdate = Post(
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

        //добавляем пост
        WallService.add(postOne)
        //пытаемся изменить пост с новым id
        val falseUpdate = WallService.update(postTwo)
        assertTrue(falseUpdate)
        //пытаемся изменить пост с существующим id
        val trueUpdate = WallService.update(postOneUpdate)
        assertTrue(trueUpdate)
    }
}