import java.util.Scanner;

/**
 * Класс BattleArena (Основной класс с методом main)
 *
 * ОПИСАНИЕ НА РУССКОМ:
 * 1) В методе main() создаём объект Player и объект Enemy.
 * 2) Организуем логику пошаговой (или цикличной) битвы между игроком и врагом.
 * 3) Можно использовать Scanner для ввода действий игрока:
 *    - Выбор между действиями: атаковать, лечиться, бежать и т.д.
 * 4) Закончить цикл, когда игрок или враг мертвы (health <= 0).
 * 5) Вывести результат битвы.
 */
public class BattleArena {
    public static void main(String[] args) {
        // TODO:
        // Примерные шаги:
        // 1) Спросить у пользователя имя игрока (через Scanner).
        // 2) Создать объект Player (new Player(...)).
        // 3) Создать объект Enemy (new Enemy(...)).
        // 4) Организовать цикл битвы (while player.isAlive() && enemy.isAlive()).
        //    - Спросить у пользователя, что делать:
        //      (1) Атаковать врага, (2) Восстановить здоровье (optional), (3) Сбежать (optional) и т.д.
        //    - Выполнить соответствующие методы (attack, takeDamage).
        //    - Проверить, жив ли игрок/враг, вывести статусы.
        // 5) По окончании цикла вывести победителя или причину завершения игры.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваше имя игрока: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName, 100, 10);

        Enemy goblin = new Enemy("Гоблин", 30, 5);

        System.out.println("Дикий злобный " + goblin.getName() + " появился с " + goblin.getHealth() + "HP!");

        boolean finished = false;
        while (player.isAlive() && goblin.isAlive()) {
            System.out.println("\n=============== BATTLE MENU ================");
            System.out.println("1. Атаковать");
            System.out.println("2. Похилиться");
            System.out.println("3. Выйти из игры");

            System.out.print("Выберите действие: ");
            int action = scanner.nextInt();

            if (action == 1) {
                player.attack(goblin);
                if (goblin.isAlive()) {
                    goblin.attack(player);
                }
            } else if (action == 2) {
                player.heal(6);
                if (goblin.isAlive()) {
                    goblin.attack(player);
                }
            } else if (action == 3) {
                finished = true;
            }

            if (player.isAlive() && goblin.isAlive() && !finished) {
                System.out.println("\n--- Статус игры ---");
                System.out.println(player.getName() + " -> HP: " + player.getHealth());
                System.out.println(goblin.getName() + " -> HP: " + goblin.getHealth());
            }

            if (finished) {
                break;
            }
        }

        if (finished) {
            System.out.println("Жаль, что вы покинули игру! Приходите еще :)");
        } else {
            if (!player.isAlive()) {
                System.out.println(player.getName() + " повержен! Game Over.");
            } else if (!goblin.isAlive()) {
                System.out.println(player.getName() + " победил " + goblin.getName() + "! Вы выиграли :3");
            }
        }

        scanner.close();
    }
}
