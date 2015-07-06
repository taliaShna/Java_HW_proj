package Ride;

import race.Controller;
import race.Model;
import race.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RaceStage extends Stage {
	
	public RaceStage(){
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true);
		setViewport(new ScreenViewport(camera));
	}
	
	public void init(){
		Model model = new Model();

		final Controller controller = new Controller();

		model.addListener(controller);
		final BackgroundImage background = new BackgroundImage(1);
		RaceStage.this.addActor(background);

		View view = new View() {
			
			private Box[][] _boxes = new Box[Model.ROWS][Model.COLUMS];

			@Override
			protected void drawBox(int colorIndex, int row, int col){
				if (row < ROW_COUNT && row >= 0 && col < COL_COUNT && col >= 0){
						Box box = new Box(colorIndex);
						_boxes[row][col] = box;
						RaceStage.this.addActor(box);
						box.setBounds(col * Box.SIZE, row * Box.SIZE, Box.SIZE, Box.SIZE);
				
					_boxes[row][col].setColor(colorIndex);
				}
				
			}
			
			@Override
			protected void drawRider(final int col){
				Rider rider = new Rider();
				RaceStage.this.addActor(rider);
				rider.setBounds(col * Box.SIZE, (Model.ROWS - 1) * Box.SIZE, Box.SIZE, Box.SIZE);
			}
			
			@Override
			protected void drawField(){
				for(int i = 0; i < ROW_COUNT; i++){
					for(int j = 0; j < COL_COUNT; j++){
						if (_boxes[i][j] != null)
						_boxes[i][j].remove();
						_boxes[i][j] = null;
						}
					}
			}
			
		};
		
		controller.setView(view);
		controller.setModel(model);

		Timer.schedule(new Timer.Task() {
			@Override
			public void run(){
				controller.moveDown();
			}
		}, 0.5f, 0.5f);
		
		Gdx.input.setInputProcessor(this);
		addListener(new InputListener() {
			
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch(keycode){
				case Input.Keys.LEFT:
					controller.moveLeft();
					break;
				case Input.Keys.RIGHT:
					controller.moveRight();
					break;	
				}
				return true;
			}
		});
	}
	
}