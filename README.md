# BooleanQueryParser

## 1. What is it?

BooleanQueryParser (BQP) is a software that deals with simple boolean algebra.
It contains a small domain specific language that is almolst the same as Java ([link](https://introcs.cs.princeton.edu/java/11precedence/)).<br/><br/>
**N.B** For now, the only operators that are supported are following:
- relational:&nbsp;**< > <= >=** 
- equality:&nbsp;&nbsp;&nbsp;**== !=**
- and or: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**&& ||**

## 2. Demo

```java
//Get data
Map<String, Object> valueMap = new HashMap<String, Object>();
valueMap.put("A", false);
valueMap.put("C", true);
valueMap.put("D", true);
valueMap.put("E", "elo");

//Create query
String query = "A || $true == (C && D) || $false";

//Run it
QueryRunner qr = new QueryRunner();
Boolean result = qr.runQuery(query, valueMap);
//true
System.out.println(result);

```




Proper readme will be added soon™
