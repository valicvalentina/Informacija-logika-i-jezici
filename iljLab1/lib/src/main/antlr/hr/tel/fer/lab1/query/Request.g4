grammar Request;

@header {
package hr.tel.fer.lab1.query; 
}

request: 'FILTER' expr* 'RETURN' counter;

counter: STRING ;

expr: KEY OP value ;

value: STRING ;

STRING: '"' (ESC|.)*? '"' ;
ESC : '\\"' | '\\\\' ; 

KEY: 'IP' | 'DATETIME' | 'METHOD' | 'VERSION' | 'STATUS' ;
OP: '==' | '!=' ;
WS : [ \t\r\n]+ -> skip ;
