package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Core.ExpressionBuilder;
import Core.ExpressionEvaluator;
import Core.StringExpressionParser;
import Core.Interfaces.IExpressionEvaluator;
import Models.ArithmeticExpression;
import Models.AbstractExpression.Operator;


public class ExpressionBuilderTester extends BaseTester {

	@Test
	public void test() {
		IExpressionEvaluator evaluator = new ExpressionEvaluator();
		
		ArithmeticExpression exp = BuildExpressionOne();
		Double result = evaluator.evaluate(exp);
		assertTrue(result == 15);

		exp = BuildExpressionTwo();
		result = evaluator.evaluate(exp);
		assertTrue(Equals(result, 1.38632142));
		
		exp = BuildExpressionThree();
		result = evaluator.evaluate(exp);
		assertTrue(result == 5);
		
		exp = BuildExpressionFour();
		result = evaluator.evaluate(exp);
		assertTrue(Equals(result, 1.38632142));
	}

	
	// * + / 5 3 1 / + 8 2 - 3 1	= 15
	private ArithmeticExpression BuildExpressionOne() {
		ExpressionBuilder builder = new ExpressionBuilder();
		builder.addOperator(Operator.Multiplication);
		builder.addOperator(Operator.Sum);
		builder.addOperator(Operator.Difference);
		builder.addValue(Double.valueOf(5));
		builder.addValue(Double.valueOf(3));
		builder.addValue(Double.valueOf(1));
		builder.addOperator(Operator.Division);
		builder.addOperator(Operator.Sum);
		builder.addValue(Double.valueOf(8));
		builder.addValue(Double.valueOf(2));
		builder.addOperator(Operator.Difference);
		builder.addValue(Double.valueOf(3));
		builder.addValue(Double.valueOf(1));	
		return (ArithmeticExpression) builder.build();
	}
	
	// * + 3.14 3.67 / 4.56 22.4	= 1.3863
	private ArithmeticExpression BuildExpressionTwo() {
		ExpressionBuilder builder = new ExpressionBuilder();
		builder.addOperator(Operator.Multiplication);
		builder.addOperator(Operator.Sum);
		builder.addValue(3.14);
		builder.addValue(3.67);
		builder.addOperator(Operator.Division);
		builder.addValue(4.56);
		builder.addValue(22.4);
		return (ArithmeticExpression) builder.build();
	}
	
	// + 4 1	= 5
	private ArithmeticExpression BuildExpressionThree() {
		ExpressionBuilder builder = new ExpressionBuilder();
		builder.addOperator(Operator.Sum);
		builder.addValue(Double.valueOf(4));
		builder.addValue(Double.valueOf(1));
		return (ArithmeticExpression)builder.build();
	}
	
	
	private ArithmeticExpression BuildExpressionFour() {
		StringExpressionParser parser = new StringExpressionParser();
		ExpressionBuilder builder = new ExpressionBuilder(parser);
		builder.startListening();
		parser.parse("* + - 4.14 * 1 1 + 2.6 1.07 / 4.56 + * * 2 + - / + 200 * 4 * 20 10 / 200 1 * 0.1 2 0.7 / 10 5 0.4");
		while (!builder.canBuild())
			continue;
		return (ArithmeticExpression)builder.build();
	}
	
}
