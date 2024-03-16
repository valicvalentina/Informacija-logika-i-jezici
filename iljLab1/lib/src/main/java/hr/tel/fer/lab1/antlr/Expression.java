package hr.tel.fer.lab1.antlr;

import hr.tel.fer.lab1.logging.LogEntry;

public class Expression {
  private String key;
  private Operator operator;
  private String value;

  public Expression() {
  }

  public Expression(String key, Operator operator, String value) {
    this.key = key;
    this.operator = operator;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
   
  
  public boolean checkCondition(LogEntry entry) {
    int comparison = compare(entry);
       switch (operator) {
          case EQ:
          return comparison == 0;
          case NEQ:
          return comparison != 0;
          }
       return false;
  }

  private int compare(LogEntry entry) {
		int comparison = 0;
		if(key.equals("STATUS")) {
			int intValue = Integer.parseInt(value);
			comparison = entry.getStatus() - intValue;
		} else if(key.equals("IP") && value.contains("X")) {
			   String result = "";
			char[] find = value.toCharArray();
			    for(int i = 0; i < find.length; i++) {
				    if(find[i] != 'X') {
					result += String.valueOf(find[i]);
				  } else {
					break;
				}
			}
			if(entry.getIp().startsWith(result)) {
				comparison = 0;
			} else {
				comparison = 1;
			}
		} else {
			String entryValue = getStringValueFromEntry(entry);
			comparison = entryValue.compareTo(value);
		}
		return comparison;
	}

	private String getStringValueFromEntry(LogEntry entry) {
		switch(key) {
			case "IP":
				return entry.getIp();
			case "DATETIME":
				return entry.getDateTimeAsString();
			case "METHOD":
				return entry.getMethod();
			case "VERSION":
				return entry.getVersion();
			case "STATUS":
				return Integer.toString(entry.getStatus());
		}
		return null;
	}
}