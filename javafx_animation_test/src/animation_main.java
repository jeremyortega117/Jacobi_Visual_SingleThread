
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class animation_main extends Application{


	@Override
	public void start(Stage primaryStage){

		int leftValue = 20;
		int topValue = 20;
		int Scale = 50;
		int X_by_X = 25;
		int cRed = 200;
		int cGreen = 100;
		int cBlue = 0;

		int biggestSide = leftValue > topValue ? leftValue : topValue;

		// create root object
		Pane root = new Pane();

		// create array of circle objects
		Circle[][] circDup = new Circle[X_by_X][X_by_X];
		Jacobi_Model JM = new Jacobi_Model(leftValue, topValue, Scale, X_by_X);
		JM.preset();
		JM.iterate();
		double[][] table = JM.getTable();


		// initialize each individual object
		// set array index to newly created object
		for(int i = 0; i < X_by_X; i++){
			for(int j = 0; j < X_by_X; j++){
				Circle circ = new Circle();
				if(i == 0 || j == 0){
					circ.setRadius(table[i][j]);
					circ.setFill(Color.RED);
				}
				else{
					circ.setRadius(1);
					circ.setFill(Color.BLUE);
				}
				circ.setLayoutX(i*Scale+biggestSide);
				circ.setLayoutY(j*Scale+biggestSide);
				circDup[i][j] = circ;
			}
		}

		// create and initialize a new transition object for each element in the array
		for(int i = 0; i < X_by_X; i++){
			for(int j = 0; j < X_by_X; j++){
				ScaleTransition scale = new ScaleTransition(Duration.seconds(4), circDup[i][j]);
				FillTransition colorChange;
				System.out.println("A");
				if(i == 0 || j == 0){
					if(leftValue <= topValue){
						if(i == 0){
							colorChange = new FillTransition(Duration.seconds(4),
									circDup[i][j], Color.rgb (cRed * (leftValue/topValue) , cGreen, cBlue),
									Color.rgb(cRed * (leftValue/topValue), cGreen, cBlue));
						}
						else{
							colorChange = new FillTransition(Duration.seconds(4),
									circDup[i][j], Color.rgb(cRed, cGreen, cBlue), Color.rgb(cRed, cGreen, cBlue));
						}
					}
					else{
						colorChange = new FillTransition(Duration.seconds(4),
							circDup[i][j], Color.rgb(cRed, cGreen, cBlue), Color.rgb(cRed,cGreen, cBlue));
					}
					scale.setToX(1);
					scale.setToY(1);
				}
				else{
					int redTemp = (int) Math.round(table[i][j]);

					colorChange = new FillTransition(Duration.seconds(4),
							circDup[i][j], Color.rgb(0, 0, 200), Color.rgb(redTemp*10, 100, 200 - (redTemp*10)));

					scale.setToX(1+table[i][j]);
					scale.setToY(1+table[i][j]);
				}
				scale.setCycleCount((int) 1f);
				scale.setNode(circDup[i][j]);
				scale.play(); // transition.pause();
				colorChange.play();
				root.getChildren().add(circDup[i][j]);
			}
		}
		System.out.println("A");

		// add the root to the scene
		Scene scene = new Scene(root, X_by_X*Scale, X_by_X*Scale);

		// start scene
		primaryStage.setTitle("Array circle test");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		launch(args);
	}

}
