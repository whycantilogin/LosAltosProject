package App.main;

// import javax.swing.*;
// import java.awt.*;

// import java.util.Scanner;

public class Disease {
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
}

class LungCancer{
    //per kilogram of body weight
    public int suggestedMinCalories = 25;
    public int suggestedMaxCalories = 30;
    public double suggestedMinProtien = 1.0;
    public double suggestedMaxProtien = 1.5;
}
class colorectalCancer{
        //in nmol/L
        public int suggestedMinVitaminD = 75;
        public int suggestedMaxVitaminD = 100;
}
class pancreaticCancer{
    //64 ounces of fluid
    public int suggestedFluid = 64;
    
    //high protien
    public double suggestedMinProtien = 1.0;
    public double suggestedMaxProtien = 1.5;
    //less sugar, in grams
    public int suggestedSugar = 50;
}
class esophagealCancer{
    //high calories and protien
    public int suggestedMinCalories = 2000;
    public int suggestedMaxCalories = 2800;

    public double suggestedMinProtien = 1.0;
    public double suggestedMaxProtien = 1.5;

}
class kidneyCancer{
    // more fruits & vegetables
    public double suggestedMinFruits = 2;
    public double suggestedMinVegetables = 2.5;
    //more calories
    public int suggestedMinCalories = 1600;
    public int suggestedMaxCalories = 3000;
}
class stomachCancer{
    //high protien and calories
    public double suggestedMinProtien = 1.2;
    public double suggestedMaxProtien = 1.5;

    public int suggestedMinCalories = 1600;
    public int suggestedMaxCalories = 3000;  
    //more vitamin C, in mg
    public int suggestedVitaminC = 100;
    //more fiber, in g
    public int suggestedFiber = 10;

}
class uterineCancer{
    //more fruit, vegetable, grain

    //low meat and diary
    // less fat
}