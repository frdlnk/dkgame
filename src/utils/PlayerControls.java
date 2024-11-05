package utils;

import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;

public class PlayerControls {
	private double direccionHorizontal;
	private double direccionVertical;
	private boolean isJump;
	
	private int leftKey;
	private int rightKey;
	private int upKey;
	private int downKey;
	private int jumpKey;
	
	public PlayerControls() {
		this(Key.A,Key.D, Key.W,Key.S,Key.SPACE);
	}
	
	public PlayerControls(int leftKey, int rightKey, int upKey, int downKey, int jumpKey) {
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
		this.downKey = downKey;
		this.jumpKey = jumpKey;
		direccionHorizontal = 0;
		direccionVertical = 0;
		isJump = false;
	}

	public void actualizar() {
		if (InputKeyboard.isKeyPressed(rightKey)) {
			direccionHorizontal = 1;
		}else if(InputKeyboard.isKeyPressed(leftKey)) {
			direccionHorizontal = -1;
		}else {
			direccionHorizontal = 0;
		}
		if (InputKeyboard.isKeyPressed(upKey)) {
			direccionVertical = -1;
		}else if(InputKeyboard.isKeyPressed(downKey)) {
			direccionVertical = 1;
		}else {
			direccionVertical = 0;
		}
		
		isJump = InputKeyboard.isDown(jumpKey);
	}

	public double getDireccionHorizontal() {
		return direccionHorizontal;
	}

	public void setDireccionHorizontal(double direccionHorizontal) {
		this.direccionHorizontal = direccionHorizontal;
	}

	public double getDireccionVertical() {
		return direccionVertical;
	}

	public void setDireccionVertical(double direccionVertical) {
		this.direccionVertical = direccionVertical;
	}

	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getUpKey() {
		return upKey;
	}

	public void setUpKey(int upKey) {
		this.upKey = upKey;
	}

	public int getDownKey() {
		return downKey;
	}

	public void setDownKey(int downKey) {
		this.downKey = downKey;
	}

	public int getJumpKey() {
		return jumpKey;
	}

	public void setJumpKey(int jumpKey) {
		this.jumpKey = jumpKey;
	}
	
}
