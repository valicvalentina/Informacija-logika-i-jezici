grammar Request;

@header {
package hr.fer.ilj.antlr; 
}

request: 'filter' expr* ;

expr: KEY OP value ;

value: STRING ;

STRING: '"' (ESC|.)*? '"' ;
ESC : '\\"' | '\\\\' ; 

KEY: 'time' | 'level' | 'message' | 'thread' | 'pid' ;
OP: '<=' | '>=' | '<' | '>' | '=' ;
WS : [ \t\r\n]+ -> skip ;