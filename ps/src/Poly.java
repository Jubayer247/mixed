public class Poly {
    public static void main(String...args){
        Animal a=new BlackDog();
        a.color();
    }
}

class Cat extends Animal{
    void color(){
        System.out.println("Cat");
    }
}

class Animal {
    void color(){
        System.out.println("Animal");
    }

}


class Dog extends Animal {
    void color(){
        System.out.println("Dog");
    }

}

class BlackDog extends Dog{
    void color(){
        System.out.println("Black Dog");
    }
}