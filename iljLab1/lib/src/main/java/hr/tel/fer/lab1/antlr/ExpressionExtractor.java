package hr.tel.fer.lab1.antlr;

import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;

import hr.tel.fer.lab1.antlr.*;
import hr.tel.fer.lab1.query.RequestBaseListener;
import hr.tel.fer.lab1.query.RequestParser.ExprContext;

public class ExpressionExtractor extends RequestBaseListener {
  private List<Expression> expressions = new LinkedList<>();
  private String error;

  public List<Expression> getExpressions() {
    return expressions;
  }

  @Override
  public void exitExpr(ExprContext ctx) {
    Expression expr = new Expression();
    expr.setKey(ctx.KEY().getText());
    String value = ctx.value().getText();
    expr.setValue(value.substring(1, value.length() - 1));
    expr.setOperator(Operator.parse(ctx.OP().getText()));
    expressions.add(expr);
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
    error = "Request not in format!";
  }

  public String getError() {
    return error;
  }
}