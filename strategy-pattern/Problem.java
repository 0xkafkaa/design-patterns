abstract class Duck{
    public void quack(){
        System.out.println("Duck says Quack");
    };
    public void swim(){
        System.out.println("Duck is swimming");
    }
    /*
    what joe thought would be right localised update to the code ended up causing non local side effect which is now that the ducks which would not fly ended by flying
     */    
    public void fly(){
        System.out.println("Duck is flying");
    }
    public abstract void display();    
}

class MallardDuck extends Duck{    
    public void display() {
        System.out.println("Duck looks like a Mallard");
    }
}

class ReadHeadDuck extends Duck{
    public void display(){
        System.out.println("Duck looks like a Redhead");
    }
}

class RubberDuck extends Duck{
    @Override
    public void quack(){
        System.out.println("Duck is squeaking");
    }
    @Override
    public void fly(){
        System.out.println("Duck isn't flying, since I am a Rubberduck");
    }
    public void display(){
        System.out.println("Duck looks like a Rubberduck");
    }        
}

class DecoyDuck extends Duck{
    @Override
    public void quack(){
        System.out.println("Duck says nothing");
    }
    @Override
    public void fly(){
        System.out.println("Duck isn't flying, since I am a Decoyduck");
    }
    public void display(){
        System.out.println("Looks like a Decoyduck");
    }
}
class Problem{
    public static void main(String[] args){
        MallardDuck md = new MallardDuck();
        md.display();
        md.quack();
        md.swim();
        md.fly();

        ReadHeadDuck rhd = new ReadHeadDuck();
        rhd.display();
        rhd.quack();
        rhd.swim();
        rhd.fly();

        RubberDuck rd = new RubberDuck();
        rd.display();
        rd.quack();
        rd.swim();
        rd.fly();

        DecoyDuck dd = new DecoyDuck();
        dd.display();
        dd.quack();
        dd.swim();
        dd.fly();
    }
}
