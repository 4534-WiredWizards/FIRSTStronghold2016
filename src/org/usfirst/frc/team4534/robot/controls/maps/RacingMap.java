package org.usfirst.frc.team4534.robot.controls.maps;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;
import org.usfirst.frc.team4534.robot.ControlSystem.ButtonLiteral;
import org.usfirst.frc.team4534.robot.controls.ControlMap;

public class RacingMap implements ControlMap {

	@Override
	public ButtonLiteral convertButton(Button button) {
		switch (button) {
		case ARM_DOWN:
			return ButtonLiteral.STICK_RIGHT_DOWN;
		case ARM_EXTEND:
			return ButtonLiteral.A;
		case ARM_RETRACT:
			return ButtonLiteral.X;
		case ARM_UP:
			return ButtonLiteral.STICK_RIGHT_UP;
		case MOVE_BACKWARD:
			return ButtonLiteral.LEFT_TRIGGER;
		case MOVE_FORWARD:
			return ButtonLiteral.RIGHT_TRIGGER;
		case PRECISION:
			return ButtonLiteral.STICK_LEFT_DOWN;
		case SHOOT:
			return ButtonLiteral.RIGHT_BUMPER;
		case TURBO:
			return ButtonLiteral.STICK_LEFT_UP;
		case TURN_LEFT:
			return ButtonLiteral.STICK_LEFT_LEFT;
		case TURN_RIGHT:
			return ButtonLiteral.STICK_LEFT_RIGHT;
		default:
			return ButtonLiteral.A;
		}
	}

}
