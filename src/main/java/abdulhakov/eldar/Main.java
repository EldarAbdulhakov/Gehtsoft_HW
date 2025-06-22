package abdulhakov.eldar;

public class Main {

    public static void main(String[] args) {
        HelloWorld.HelloWorldPrint();

        CustomList customList = new CustomList();
        customList.add(1);
        customList.add(24);
        customList.add(39);
        customList.print();
        System.out.println("Is Empty: " + customList.isEmpty());

        System.out.println("Size: " + customList.size());

        System.out.println("Index 1: " + customList.get(1));
        System.out.println("Index 2: " + customList.get(2));

        customList.set(1, 150);
        customList.print();

        customList.remove(2);
        customList.print();

        customList.clear();
        customList.print();
        System.out.println("Is Empty: " + customList.isEmpty());

        customList.add(55);
        customList.print();
    }
}
