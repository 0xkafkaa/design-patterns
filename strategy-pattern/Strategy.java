class Strategy{
  public static void main(String[] args){
    Duck wildDuck = new Duck("WildDuck", new CityFlying(), new WildWalking());
    System.out.println(wildDuck.Name());
    System.out.println(wildDuck.Fly());
    System.out.println(wildDuck.Walk());
  }
}

interface FlyBehaviour{
  public String Fly();
}

interface WalkBehaviour{
  public String Walk();
}

class WildWalking implements WalkBehaviour{
  public String Walk(){
    return "I walk wildly";
  }
}

class WildFlying implements FlyBehaviour{
  public String Fly(){
    return "I fly wildly";
  }
}

class CityWalking implements WalkBehaviour{
  public String Walk(){
    return "I walk as a city duck";
  }
}

class CityFlying implements FlyBehaviour{
  public String Fly(){
    return "I fly as a city duck";
  }
}


interface Ducks{
  public String Fly();
  public String Walk();
}

class Duck implements Ducks{
  FlyBehaviour fb;
  WalkBehaviour wb;
  String name;
  Duck(String name, FlyBehaviour fb, WalkBehaviour wb){
    this.name = name;
    this.fb = fb;
    this.wb = wb;
  }
  public String Name(){
    return this.name;
  }
  public String Fly(){
    return this.fb.Fly();
  }
  public String Walk(){
    return this.wb.Walk();
  }
}