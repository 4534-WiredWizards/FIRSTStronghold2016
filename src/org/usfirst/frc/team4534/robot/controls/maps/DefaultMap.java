package org.usfirst.frc.team4534.robot.controls.maps;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;
import org.usfirst.frc.team4534.robot.ControlSystem.ButtonLiteral;
import org.usfirst.frc.team4534.robot.controls.ControlMap;

public class DefaultMap implements ControlMap {

	@Override
	public ButtonLiteral convertButton(Button button) {
		switch (button) {
		case ARMS_DOWN:
			return ButtonLiteral.STICK_RIGHT_DOWN;
		case RIGHT_ARM_TOGGLE:
			return ButtonLiteral.RIGHT_BUMPER;
		case LEFT_ARM_TOGGLE:
			return ButtonLiteral.LEFT_BUMPER;
		case ARMS_UP:
			return ButtonLiteral.STICK_RIGHT_UP;
		case MOVE_BACKWARD:
			return ButtonLiteral.STICK_LEFT_DOWN;
		case MOVE_FORWARD:
			return ButtonLiteral.STICK_LEFT_UP;
		case PRECISION:
			return ButtonLiteral.LEFT_TRIGGER;
		case SHOOT:
			return ButtonLiteral.A;
		case TURBO:
			return ButtonLiteral.RIGHT_TRIGGER;
		case INTAKE:
			return ButtonLiteral.B;
		case TURN_LEFT:
			return ButtonLiteral.STICK_LEFT_LEFT;
		case TURN_RIGHT:
			return ButtonLiteral.STICK_LEFT_RIGHT;
		default:
			return ButtonLiteral.A;
		}
	}

}
