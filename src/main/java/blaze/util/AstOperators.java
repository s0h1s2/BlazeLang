/*
 ** Dec 27,2022 Shkar Sardar
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 **
 */

package blaze.util;

public abstract class AstOperators {
    public enum AstBinaryOperator {
        OPERATOR_ADD, OPERATOR_SUBTRACT, OPERATOR_MULTIPLY, OPERATOR_DIVIDE, OPERATOR_EQUAL, OPERATOR_NOEQUAL,
        OPERATOR_LEQ, OPERATOR_LE, OPERATOR_GEQ, OPERATOR_GE, OPERATOR_BITWISE, OPERATOR_BITOR, OPERATOR_BITAND,
        OPERATOR_AND, OPERATOR_OR, OPERATOR_GREATERTHAN, OPERATOR_LESSTHANEQUAL, OPERATOR_GREATERTHANEQUAL,
        OPERATOR_LESSTHAN,
    }

    public enum AstUnaryOperator {
        OPERATOR_DEREFERENCE, OPERATOR_GETADDRESS, OPERATOR_NEGATIVE, OPERATOR_POSITIVE,
    }

    public enum AstModifyOperator {
        OPERATOR_INCREMENT_POSTFIX, OPERATOR_DECREMENT_POSTFIX
    }

}
