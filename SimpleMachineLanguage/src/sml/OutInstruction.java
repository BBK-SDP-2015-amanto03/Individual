package sml;

//////////////////////////////////////
//Class: OutInstruction
//Coursework1
//Author: Ahsan Manto
/////////////////////////////////////

public class OutInstruction extends Instruction {

	private int result;

	public OutInstruction(String label, String op) {
		super(label, op);
	}

	public OutInstruction(String label, int result) {
		this(label, "out");
		this.result = result;
		
		Global.resultOut = result;
	}

	@Override
	public void execute(Machine m) {
		//int value1 = m.getRegisters().getRegister(op1);
		//int value2 = m.getRegisters().getRegister(op2);
		//m.getRegisters().setRegister(result, value1 * value2);
		//value1 = m.getRegisters().getRegister(result);
	}

	@Override
	public String toString() {
		return super.toString() + " from register " + Global.resultOut;

	}
}