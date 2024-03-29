package Ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GOScreen implements Screen{
    final RideGame _game;

    public GOScreen(final RideGame game) {
        _game = game;
    }
    
	GOStage _stage;
	
	@Override
	public void show() {
		_stage = new GOStage();
		_stage.init();	
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   	
      
        _stage.act(delta);
        _stage.draw();
	
	}

	@Override
    public void resize(int width, int height) {
		_stage.getViewport().update(width, height, true);
    }

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
