# kotlin2.2.1.posts
Задача №1 - Посты
Наконец мы добрались до ООП и можем уже не только решать вычислительные задачи, но и моделировать целые системы.

На лекции мы разобрали упрощённый пример того, как может выглядеть пост, давайте же посмотрим на то, как он выглядит на самом деле. Возьмите себе за правило анализировать системы, с которыми вы работаете в реальной жизни, и продумывать, как бы сделали вы.

В качестве примера возьмём всё тот же VKontakte: https://vk.com/dev/objects/post Если страница недоступна, воспользуйтесь копией из каталога assets).

На что нужно обратить внимание:

В Kotlin мы используем camelCase для полей.
Некоторые поля помечены как integer [0, 1], хотя по логике, должны быть Boolean (у вас должны быть Boolean).
Игнорируйте поля post_source, attachments, geo, copy_history.
Все остальные поля должны быть перечислены.
Что мы хотим получить:
Data класс Post.
Объект WallService, который внутри себя хранит посты в массиве.
