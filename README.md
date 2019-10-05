# BooleanQueryParser

## 1. What is it?

BooleanQueryParser (BQP) is a software that deals with simple boolean algebra.
It contains a small domain specific language that is almolst the same as Java ([link](https://introcs.cs.princeton.edu/java/11precedence/)).<br/><br/>
**N.B** For now, the only operators that are supported are following:
- relational:&nbsp;**< > <= >=** 
- equality:&nbsp;&nbsp;&nbsp;**== !=**
- and or: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**&& ||**

## 2. How it works?
It is absolutely trivial to use. 
```java
String query = "A || $true == (C && D) || $false";
QueryRunner qr = new QueryRunner();
Boolean result = qr.runQuery(query, valueMap);
```
There are 3 important components
- **Map of parameters**: This collection contains parameters that are going to be used to resolve the query. It always has to be Java map with String as a key and Object as a value (**java.util.Map<String, Object>**).
- **Query string**: Query save as java.lang.String. E.g **"A || $true == (C && D) || $false"**
- **QueryRunner object**: **stargarth.interpreter.QueryRunner** is an engine that interprets the query/AST. It takes string query/AST as and parameter map as an input. It evaluates the query and returns boolean as an output.<br/><br/>

## 3. Demo
There are two ways of running queries:
<br/>&nbsp;&nbsp;&nbsp;1. Run it as it is
<br/>&nbsp;&nbsp;&nbsp;2. Build abstract syntax tree (AST) from query string and use it to run queries

**stargarth.parser.AbstractSyntaxTree** is an internal query representation that **QueryRunner** is using to evaluate the query.<br/>
Running queries with AST as an input has huge benefits when same query is being run multiple times. This is due to the fact that whenever you run with AST rather than query string you skip whole parsing phase. 

1 Run query as is:
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

valueMap.put("C", false);
result = qr.runQuery(query, valueMap);
//false
System.out.println(result);
```
2 Create AST and utilize use it as input for runner:
```java
Map<String, Object> valueMap = new HashMap<String, Object>();
valueMap.put("A", false);
valueMap.put("C", true);
valueMap.put("D", true);
valueMap.put("E", "elo");

//Create query
String query = "A || $true == (C && D) || $false";

//Generate AST
QueryRunner qr = new QueryRunner();
AbstractSyntaxTree ast = qr.generateAst(query);
//Use it to run query
Boolean result = qr.runQuery(ast, valueMap);
//true
System.out.println(result);
		
valueMap.put("C", false);
result = qr.runQuery(ast, valueMap);
//false
System.out.println(result);

```


Proper readme will be added soon™
