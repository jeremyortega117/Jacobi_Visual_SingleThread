import java.text.DecimalFormat;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Jacobi_Model {

	private static double Height;
	private static double Width;
	private static int Size;
	private static int iterations = 50;
	private static int Scale;

	private static double[][] tableOne;
	private static double[][] tableTwo;

	private static DecimalFormat df2 = new DecimalFormat("#.##");


	// create array of circle objects
	Circle[][] circDup; //////////////////////////////////////////////


	public double[][] getTable(){
		return tableOne;
	}


	public Jacobi_Model(double height, double width, int scale, int size){
		Height = height;
		Width = width;
		Scale = scale;
		tableOne = new double[size][size];
		tableTwo = new double[size][size];
		Size = size;
		circDup = new Circle[size][size]; ////////////////////////////////////////
	}

	public void iterate(){

		boolean change = true;
		for(int i = 0; i < iterations; i++){

			if(change == true){
				updateOne();
				printTable(tableOne);
			}
			if(change == false){
				updateTwo();
				printTable(tableTwo);
			}

			if(change == true)
				change = false;
			else
				change = true;

		}
	}


	private void printTable(double[][] temp){
		for(int i = 0; i < Size; i++){
			for(int j = 0 ; j < Size; j++){
				System.out.print(df2.format(temp[i][j]) + ",");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	private void updateTwo() {
		for(int i = 1; i < Size; i++){
			for(int j = 1; j < Size;j++){

				if(i == Size-1 && j != Size-1){
					tableOne[i][j] =
							(tableTwo[i-1][j-1] +
									tableTwo[i-1][j+1]+
									tableTwo[i][j+1] +
									tableTwo[i][j-1]+
									tableTwo[i-1][j])/5;
				}
				else if(i != Size-1 && j == Size-1){
					tableOne[i][j] =
							(tableTwo[i-1][j-1] +
									tableTwo[i+1][j-1]+
									tableTwo[i][j-1]+
									tableTwo[i-1][j]+
									tableTwo[i+1][j])/5;
				}
				else if(i == Size-1 && j == Size-1){
					tableOne[i][j] =
							(tableTwo[i-1][j-1] +
									tableTwo[i][j-1]+
									tableTwo[i-1][j])/3;
				}
				else{
					tableOne[i][j] =
							(tableTwo[i-1][j-1] +
									tableTwo[i-1][j+1]+
									tableTwo[i+1][j-1]+
									tableTwo[i+1][j+1]+

									tableTwo[i][j+1] +
									tableTwo[i][j-1]+
									tableTwo[i-1][j]+
									tableTwo[i+1][j])/8;
				}
			}
		}
	}
//
//	private void updateTwo() {
//		for(int i = 1; i < Size-1; i++){
//			for(int j = 1; j < Size-1;j++){
//				tableOne[i][j] =
//						(tableTwo[i-1][j-1] +
//						tableTwo[i-1][j+1]+
//						tableTwo[i+1][j-1]+
//						tableTwo[i+1][j+1]+
//
//						tableTwo[i][j+1] +
//						tableTwo[i][j-1]+
//						tableTwo[i-1][j]+
//						tableTwo[i+1][j])/8;
//			}
//		}
//	}

	private void updateOne() {
		for(int i = 1; i < Size; i++){
			for(int j = 1; j < Size;j++){
				if(i == Size-1 && j != Size-1){
					tableTwo[i][j] =
							(tableOne[i-1][j-1] +
									tableOne[i-1][j+1]+
									tableOne[i][j+1] +
									tableOne[i][j-1]+
									tableOne[i-1][j])/5;
				}
				else if(i != Size-1 && j == Size-1){
					tableTwo[i][j] =
							(tableOne[i-1][j-1] +
									tableOne[i+1][j-1]+
									tableOne[i][j-1]+
									tableOne[i-1][j]+
									tableOne[i+1][j])/5;
				}
				else if(i == Size-1 && j == Size-1){
					tableTwo[i][j] =
							(tableOne[i-1][j-1] +
									tableOne[i][j-1]+
									tableOne[i-1][j])/3;
				}
				else{
					tableTwo[i][j] =
							(tableOne[i-1][j-1] +
									tableOne[i-1][j+1]+
									tableOne[i+1][j-1]+
									tableOne[i+1][j+1]+

									tableOne[i][j+1] +
									tableOne[i][j-1]+
									tableOne[i-1][j]+
									tableOne[i+1][j])/8;
				}
			}
		}
	}

	public void preset(){
		System.out.println("A");

		for(int i = 0; i < Size; i++){
			for(int j = 0; j < Size; j++){
				tableOne[i][j] = 0.0;
				tableTwo[i][j] = 0.0;

				if(i == 0){
					tableOne[i][j] = Height;
					tableTwo[i][j] = Height;
				}

				if(j == 0 && i != 0){
					tableOne[i][j] = Width;
					tableTwo[i][j] = Width;
				}
				 Circle circ = new Circle();
			     circ.setFill(Color.GREEN);
			     circ.setRadius(30);
			     circ.setLayoutX(i*Scale);
			     circ.setLayoutY(i*Scale);
			     circDup[i][j] = circ;
			}
		}
		System.out.println("B");
	}
}
