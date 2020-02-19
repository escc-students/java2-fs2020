// Macro Nutrient Tracker
// Coded so far by Ryan Williams
// February 12th, 2020
// This program takes input for a daily goal for calories, protein, carbs and fat intake and lets a user keep track of their daily totals side-by-side with their goal.
// Developed and compiled using Netbeans 8.2

package daily.macronutrient.tracker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;



public class DailyMacronutrientTracker extends Application {
    
    Diet currentGoal = new Diet();      // Custom objects to store information about diets across classes
    Diet dailyDiet = new Diet();
    
    Label RootCaloriesLabel = new Label("Calories Intake: " + String.valueOf(dailyDiet.getCalories()) + "   -->");      // Labels on main screen that display daily totals.
    Label RootProteinLabel = new Label("Protein Intake: " + String.valueOf(dailyDiet.getProtein()) + "   --->");
    Label RootCarbsLabel = new Label("Carbs Intake: " + String.valueOf(dailyDiet.getCarbs()) + "   ----->");
    Label RootFatLabel = new Label("Fat Intake: " + String.valueOf(dailyDiet.getFat()) + "   ------->");
    
    Label RootCaloriesGoalLabel = new Label("Calories Goal: " + String.valueOf(currentGoal.getCalories()));     // Labels on main screen that display the current goal.
    Label RootProteinGoalLabel = new Label("Protein Goal: " + String.valueOf(currentGoal.getProtein()));
    Label RootCarbsGoalLabel = new Label("Carbs Goal: " + String.valueOf(currentGoal.getCarbs()));
    Label RootFatGoalLabel = new Label("Fat Goal: " + String.valueOf(currentGoal.getFat()));
    
    @Override
    public void start(Stage primaryStage) {
        Button newGoalbtn = new Button();       // Button to create a new goal
        newGoalbtn.setText("New Goal");
        
        newGoalbtn.setOnAction(new EventHandler<ActionEvent>() {      // Event handler that creates a new goal when the new Goal button is pushed;
            @Override                                                 // See NewGoal() method below.
            public void handle(ActionEvent event) { 
                NewGoal();
            }
        });
        
        Button eatBtn = new Button("I ate");        // Button to update daily totals.
        
        eatBtn.setOnAction(new EventHandler<ActionEvent>() {        // Event handler that adjusts the totals of the total intake for the day;
            @Override                                               // See IAte() method below.
            public void handle(ActionEvent event) {
                IAte();
            }
        });
        
        GridPane root = new GridPane();     // Main container
        
        root.setVgap(15);       // Formatting for controls.
        root.setHgap(15);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        
        root.add(newGoalbtn, 1, 0);     // Add the buttons to the top of the container.
        root.add(eatBtn, 0, 0);
        
        root.add(RootCaloriesLabel, 0, 1);      // Display the labels to track totals vertically.
        root.add(RootProteinLabel, 0 , 2);
        root.add(RootCarbsLabel, 0, 3);
        root.add(RootFatLabel, 0, 4);
        
        root.add(RootCaloriesGoalLabel, 1, 1);      // Display the current goals vertically to the right of the totals.
        root.add(RootProteinGoalLabel, 1, 2);
        root.add(RootCarbsGoalLabel, 1, 3);
        root.add(RootFatGoalLabel, 1, 4);
        
        Scene scene = new Scene(root, 450, 300);        // Create the scene and specify window size.
        
        primaryStage.setTitle("Daily Macronutrient Tracker");     // Create window.  
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    
    public void NewGoal()           // This method creates a new goal and updates the goal on the main screen.
    {
        GridPane root = new GridPane();
        
        Scene newGoalScene = new Scene(root, 450, 400); // This window is a litte bigger since the controls take up more space; watch for clipping if you adjust these numbers!
        Stage newGoal = new Stage();
        
        Button setGoalbtn = new Button("Set Goal");     // Button to close screen and pass values.
        
        TextField CaloriesInput = new TextField();      // TextField input boxes for the new goal.
        TextField ProteinInput = new TextField();
        TextField CarbsInput = new TextField();
        TextField FatInput = new TextField();
        
        Label CaloriesLabel = new Label("Enter Daily Calories: ");      // Label fields that indicated which TextField is which.
        Label ProteinLabel = new Label("Enter Daily Protein: ");
        Label CarbsLabel = new Label("Enter Daily Carbs: ");
        Label FatLabel = new Label("Enter Daily Fat: ");
        
        root.setVgap(50);       // Formatting for controls.
        root.setPadding(new Insets(25, 25, 25, 15));
        
        root.add(CaloriesLabel, 0, 0);      // Add all controls in an orderly fashion.
        root.add(CaloriesInput, 1, 0);
        
        root.add(ProteinLabel, 0, 1);
        root.add(ProteinInput, 1, 1);
        
        root.add(CarbsLabel, 0, 2);
        root.add(CarbsInput, 1, 2);
        
        root.add(FatLabel, 0, 3);
        root.add(FatInput, 1, 3);
        
        root.add(setGoalbtn, 1, 4);
        
        
        
        
        newGoal.setTitle("New Goal");       // Create window.
        newGoal.setScene(newGoalScene);
        newGoal.show();
        
        setGoalbtn.setOnAction(new EventHandler<ActionEvent>() {            // Event handler for set Goal button
            @Override                                               // Sets currentGoal object values to user specified numbers.
            public void handle(ActionEvent event) {                     // Then updates the labels on the main screen and closes the window.
                currentGoal.setCalories(Double.parseDouble(CaloriesInput.getText()));
                currentGoal.setProtein(Double.parseDouble(ProteinInput.getText()));
                currentGoal.setCarbs(Double.parseDouble(CarbsInput.getText()));
                currentGoal.setFat(Double.parseDouble(FatInput.getText()));
                UpdateGoal();
                newGoal.close();
            }
        });
       
    }
    
    public void IAte()      // This method adds to the current totals on the main screen.
    {
        GridPane root = new GridPane();
        
        Scene IAteScene = new Scene(root, 450, 400);        // This window is a litte bigger since the controls take up more space; watch for clipping if you adjust these numbers!
        Stage IAte = new Stage();
        
        Button Submitbtn = new Button("Submit");  // Button to close windows and submit values.
        
        TextField CaloriesInput = new TextField();      // TextField inputs for current Totals.
        TextField ProteinInput = new TextField();
        TextField CarbsInput = new TextField();
        TextField FatInput = new TextField();
        
        Label CaloriesLabel = new Label("Enter how many calories you ate:");        // Labels to indicate which TextField is which.
        Label ProteinLabel = new Label("Enter how much protein your food had: ");
        Label CarbsLabel = new Label("Enter how many carbs your food had: ");
        Label FatLabel = new Label("Enter how much fat your food had: ");
        
        root.setVgap(50);       // Formatting for controls.
        root.setPadding(new Insets(25, 25, 25, 15));
        
        root.add(CaloriesLabel, 0, 0);      // Add each control in an orderly fashion.
        root.add(CaloriesInput, 1, 0);
        
        root.add(ProteinLabel, 0, 1);
        root.add(ProteinInput, 1, 1);
        
        root.add(CarbsLabel, 0, 2);
        root.add(CarbsInput, 1, 2);
        
        root.add(FatLabel, 0, 3);
        root.add(FatInput, 1, 3);
        
        root.add(Submitbtn, 1, 4);
        
        
        
        
        IAte.setTitle("Food Logging");          // Create window.
        IAte.setScene(IAteScene);
        IAte.show();
        
        Submitbtn.setOnAction(new EventHandler<ActionEvent>() {     // Event handler for submit button that sends values to dailyDiet object
            @Override                                               // and also updates the totals on the main screen then closes the window.
            public void handle(ActionEvent event) {
                dailyDiet.addCalories(Double.parseDouble(CaloriesInput.getText()));
                dailyDiet.addProtein(Double.parseDouble(ProteinInput.getText()));
                dailyDiet.addCarbs(Double.parseDouble(CarbsInput.getText()));
                dailyDiet.addFat(Double.parseDouble(FatInput.getText()));
                UpdateCounts();
                IAte.close();
            }
        });
    }
    
    public void UpdateCounts()      // Method to update the Labels for daily totals on the main screen
    {
        RootCaloriesLabel.setText("Calories Intake: " + String.valueOf(dailyDiet.getCalories()));
        RootProteinLabel.setText("Protein Intake: " + String.valueOf(dailyDiet.getProtein()));
        RootCarbsLabel.setText("Carbs Intake: " + String.valueOf(dailyDiet.getCarbs()));
        RootFatLabel.setText("Fat Intake: " + String.valueOf(dailyDiet.getFat()));
    }
    
    public void UpdateGoal()        // Method to update the goal.
    {
        RootCaloriesGoalLabel.setText("Calories Intake: " + String.valueOf(currentGoal.getCalories()));
        RootProteinGoalLabel.setText("Protein Intake: " + String.valueOf(currentGoal.getProtein()));
        RootCarbsGoalLabel.setText("Carbs Intake: " + String.valueOf(currentGoal.getCarbs()));
        RootFatGoalLabel.setText("Fat Intake: " + String.valueOf(currentGoal.getFat()));
    }
    
   
    public class Diet           // Class to store data about the diet across the program.
    {
        double calories, protein, carbs, fat; 
        
        Diet()      // Default constructor used at compile time
        {
            this.calories = 0;
            this.protein = 0;
            this.carbs = 0;
            this.fat = 0;
        }
        
        Diet(double calories, double protein, double carbs, double fat)     // This constructor isn't used anymore, but is kept in case it is needed later.
        {
            this.calories = calories;
            this.protein = protein;
            this.carbs = carbs;
            this.fat = fat;
        }
        
        public double getCalories() { return calories; }        // Accessor methods for all fields.
        public double getProtein(){ return protein; }
        public double getCarbs() { return carbs; }
        public double getFat() { return fat; }
        
        public void setCalories(double calories) { this.calories = calories; }          // Mutator methods for all fields.
        public void setProtein (double protein) { this.protein = protein; }
        public void setCarbs (double carbs) { this.carbs = carbs; }
        public void setFat (double fat) { this.fat = fat; }
        
        public void addCalories(double calsToAdd) { this.calories += calsToAdd; }       // Methods to add a value to each field.
        public void addProtein(double proToAdd) { this.protein += proToAdd; }
        public void addCarbs(double carbsToAdd) { this.carbs += carbsToAdd; }
        public void addFat(double fatToAdd) { this.fat += fatToAdd; }
    }
    
    
}


