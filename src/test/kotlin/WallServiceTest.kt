import attachments.Copys
import attachments.Photo
import objects.Donat
import org.junit.Test

import org.junit.Assert.*
import org.junit.rules.ExpectedException

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
            Donut(false,0,false,""),
            arrayOf(Photo(1,1,1,1,"Photo1",1657686558, arrayOf(Copys("m","Photo1", 640,480))))
        ))

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
            Donut(false,0,false,""),
            arrayOf(Photo(1,1,1,1,"Photo1",1657686558, arrayOf(Copys("m","Photo1", 640,480))))
        ))

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
            Donut(false,0,false,""),
            arrayOf(Photo(2,2,2,2,"Photo2",1657686558, arrayOf(Copys("m","Photo1", 640,480)))))
        )

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
            Donut(false,0,false,""),
            arrayOf(Photo(1,1,1,1,"Photo1",1657686558, arrayOf(Copys("m","Photo1", 640,480))))
        )

        //добавляем пост
        WallService.add(postOne)
        //пытаемся изменить пост с новым id
        val falseUpdate = WallService.update(postTwo)
        assertTrue(falseUpdate)
        //пытаемся изменить пост с существующим id
        val trueUpdate = WallService.update(postOneUpdate)
        assertTrue(trueUpdate)
    }

    @Test(expected = PostNotFoundException:: class)
    fun createComment() {
        val commentOne = objects.Comments(1,1,1657788280,"Комментарий 1", Donat(false,""),2,2,null,objects.Thread(0,true,true,true))
        WallService.createComment(10,commentOne)
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
            Donut(false, 0, false, ""),
            arrayOf(Photo(1,1,1,1,"Photo1",1657686558, arrayOf(Copys("m","Photo1", 640,480))))
        )
        assertEquals(2,4)
        WallService.add(postOne)
    }

    @Test
    fun createCommentTrueId() {
        val commentOne = objects.Comments(1,1,1657788280,"Комментарий 1", Donat(false,""),2,2,null,objects.Thread(0,true,true,true))
        WallService.createComment(1,commentOne)
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
            Donut(false, 0, false, ""),
            arrayOf(Photo(1,1,1,1,"Photo1",1657686558, arrayOf(Copys("m","Photo1", 640,480))))
        )
        WallService.add(postOne)
        assertEquals(commentOne,WallService.createComment(1, commentOne))
    }


}