package App.main;

// import javax.swing.*;
// import java.awt.*;

// import java.util.Scanner;

public class Disease {
    public double suggestedMinProtein;
    public double suggestedMaxProtein;
    public int suggestedMinCaloriesWomen;
    public int suggestedMaxCaloriesWomen;
    public int suggestedMinCaloriesMen;
    public int suggestedMaxCaloriesMen;
    public int suggestedSugar;
    public int suggestedFluid;
    public int suggestedVitaminC;
    public int suggestedMinVitaminD;
    public int suggestedMaxVitaminD ;
    public double suggestedFruit;
    public double suggestedVegetable;
//     public static void main(String[] args) {
//         KeyHandler keyHandler = new Scanner(System.in);
        
//         System.out.println("Please select a disease:");
//         System.out.println("1. Headaches");
//         System.out.println("2. Diabetes");
//         System.out.println("3. Cancers");
//         System.out.println("4. Obesity");
//         System.out.println("5. Other");

//         System.out.print("Enter the number of the disease you have or enter '5' for Other: ");
//         int diseaseChoice = scanner.nextInt();
        
//         String selectedDisease = "";
//         switch (diseaseChoice) {
//             case 1:
//                 selectedDisease = "Headaches";
//                 break;
//             case 2:
//                 selectedDisease = "Diabetes";
//                 break;
//             case 3:
//                 selectedDisease = "Cancers";
//                 break;
//             case 4:
//                 selectedDisease = "Obesity";
//                 break;
//             case 5:
//                 System.out.print("Enter the name of the disease you have: ");
//                 scanner.nextLine(); 
//                 selectedDisease = scanner.nextLine();
//                 break;
//             default:
//                 System.out.println("Invalid choice. Please enter a number between 1 and 5.");
//                 break;
//         }

//         scanner.close();
//     }

    public void general() {
        suggestedMinProtein=1.0;
        suggestedMaxProtein=1.5;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedVegetable=3.0;
    }

    public void lungCancer(){
        suggestedMinCaloriesWomen = 1600;
        suggestedMinCaloriesMen = 2000;
        suggestedMaxCaloriesWomen = 2400;
        suggestedMaxCaloriesMen=3000;
        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;
        suggestedVegetable=3.0;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedSugar=50;

    }
    public void colorectalCancer(){
            //in nmol/L
            suggestedMinVitaminD = 75;
            suggestedMaxVitaminD = 100;
            suggestedMinProtein=1.0;
        suggestedMaxProtein=1.5;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedVegetable=3.0;
    }
    public void pancreaticCancer(){
        //64 ounces of fluid
        suggestedFluid = 64;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedVegetable=3.0;
        //high protien
        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;
        //less sugar, in grams
        suggestedSugar = 50;
    }
    public void esophagealCancer(){
        //high calories and protien
        suggestedSugar=50;

        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedVegetable=3.0;
        suggestedMinCaloriesWomen = 2000;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesWomen = 2800;
        suggestedMaxCaloriesMen=2800;

        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;

    }
    public void kidneyCancer(){
        // servings of fruits and v, in cups egetables
        suggestedFruit = 2;
        suggestedVegetable = 2.5;
        //more calories
        suggestedMinCaloriesWomen = 1600;
        suggestedMaxCaloriesWomen = 2400;

        suggestedMinCaloriesMen = 2000;
        suggestedMaxCaloriesMen = 3000;

        suggestedMinProtein=1.0;
        suggestedMaxProtein=1.5;
        suggestedSugar=50;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    }
    public void stomachCancer(){
        //high protien and calories
        suggestedMinProtein = 1.2;
        suggestedMaxProtein = 1.5;

        suggestedMinCaloriesWomen = 1600;
        suggestedMaxCaloriesWomen = 2400;
        suggestedMinCaloriesMen = 2000;
        suggestedMaxCaloriesMen = 3000; 
        //more vitamin C, in mg
         int suggestedVitaminC = 100;

         suggestedSugar=50;
         suggestedMinVitaminD = 75;
         suggestedMaxVitaminD = 100;
         suggestedVegetable=3.0;
        //more fiber, in g
         int suggestedFiber = 10;

    }
    public void uterineCancer(){
        //more fruit, vegetable,  in terms of cups
         suggestedFruit = 2;
        suggestedVegetable = 2.5;

        //low meat and diary, per grams for each kg of body weight
        suggestedMinProtein = 0.8;
        suggestedMaxProtein=0.8;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    }
    
    public void bladderCancer(){ //1st to edit
        suggestedMinCaloriesWomen = 1600;
        suggestedMaxCaloriesWomen = 2400;
        suggestedMinCaloriesMen = 2000;
        suggestedMaxCaloriesMen = 3000; 
        suggestedMinProtein=1.0;
        suggestedMaxProtein=1.5;
        suggestedSugar=50;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedVegetable=3.0;
        suggestedFluid = 64;
    //grams
        // public int suggestedMinCarbon = 20;
        // public int suggestedMaxCarbon = 57;
    }

    public void brainCancer(){
    //low carb, high protein, high fat,
        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedVegetable=3.0;
    }

    public void boneCancer(){
        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedVegetable=3.0;
    }

    public void breastCancer(){
        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;
        suggestedFruit = 2;
        suggestedVegetable = 2.5;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    }

    public void cervicalCancer(){
        suggestedFruit = 2;
        suggestedVegetable = 2.5;
        suggestedMinProtein=1.0;
        suggestedMaxProtein=1.5;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    }    


    public void leukemiaCancer(){
        suggestedFruit = 3.5;
        suggestedVegetable = 3.5;
        suggestedMinProtein = 0.8;
        suggestedMaxProtein=0.8;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    }


    public void mesothelioma(){
        suggestedMinProtein = 1.0;
        suggestedMaxProtein = 1.5;
        suggestedFruit = 3.5;
        suggestedVegetable = 3.5;
        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    } 



    public void ovarianCancer(){
        suggestedFruit = 2.5;
        suggestedVegetable = 3.5;
        suggestedMinProtein = 0.8;
        suggestedMaxProtein=0.8;

        suggestedSugar=50;
        suggestedMinCaloriesMen=2000;
        suggestedMaxCaloriesMen=2400;
        suggestedMinCaloriesWomen=2000;
        suggestedMaxCaloriesWomen=2400;
        suggestedMinVitaminD = 75;
        suggestedMaxVitaminD = 100;
    }
    


    public void thyroidCancer(){
    //less iodine, in micrograms
    suggestedMinProtein = 1.0;
    suggestedMaxProtein = 1.5;

    suggestedSugar=50;
    suggestedMinCaloriesMen=2000;
    suggestedMaxCaloriesMen=2400;
    suggestedMinCaloriesWomen=2000;
    suggestedMaxCaloriesWomen=2400;
    suggestedMinVitaminD = 75;
    suggestedMaxVitaminD = 100;
    suggestedVegetable=3.0;
    }

}