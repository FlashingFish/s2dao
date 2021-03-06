DROP TABLE LARGE_TEXT;
CREATE TABLE LARGE_TEXT (
    ID              INTEGER NOT NULL
   ,LARGE_STRING    CLOB
   ,VERSION_NO      INTEGER NOT NULL
   ,CONSTRAINT PK_LARGE_TEXT PRIMARY KEY (ID)
);

DROP TABLE LARGE_BINARY;
CREATE TABLE LARGE_BINARY (
    ID              INTEGER NOT NULL
   ,LARGE_BINARY    BLOB
   ,VERSION_NO      INTEGER NOT NULL
   ,CONSTRAINT PK_LARGE_BINARY PRIMARY KEY (ID)
);

DROP TABLE DEFAULT_TABLE;
CREATE TABLE DEFAULT_TABLE (
    ID              INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
   ,AAA             VARCHAR(20) DEFAULT 'ABC'
   ,BBB             VARCHAR(20)
   ,VERSION_NO      INTEGER NOT NULL
   ,CONSTRAINT PK_DEFAULT_TABLE PRIMARY KEY (ID)
);

DROP TABLE PK_ONLY_TABLE;
CREATE TABLE PK_ONLY_TABLE (
    AAA            INTEGER NOT NULL
   ,BBB            INTEGER NOT NULL
   ,CONSTRAINT PK_PK_ONLY_TABLE PRIMARY KEY (AAA, BBB)
);

DROP PROCEDURE PROCEDURE_TEST_AAA1;
CREATE PROCEDURE PROCEDURE_TEST_AAA1 (
        OUT FOO VARCHAR(20)
    )
    PARAMETER STYLE JAVA NO SQL 
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureAaa1'
;

DROP PROCEDURE PROCEDURE_TEST_AAA2;
CREATE PROCEDURE PROCEDURE_TEST_AAA2 (
        OUT BBB VARCHAR(20)
       ,OUT CCC TIMESTAMP
    )
    PARAMETER STYLE JAVA NO SQL 
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureAaa2'
;
DROP PROCEDURE PROCEDURE_TEST_AAA3;
CREATE PROCEDURE PROCEDURE_TEST_AAA3 ()
    PARAMETER STYLE JAVA NO SQL 
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureAaa3'
;

DROP PROCEDURE PROCEDURE_TEST_BBB1;
CREATE PROCEDURE PROCEDURE_TEST_BBB1 (
        IN CCC VARCHAR(20)
    )
    PARAMETER STYLE JAVA NO SQL 
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureBbb1'
;

DROP PROCEDURE PROCEDURE_TEST_BBB2;
CREATE PROCEDURE PROCEDURE_TEST_BBB2 (
        IN CCC VARCHAR(20)
       ,IN DDD NUMERIC
       ,IN EEE TIMESTAMP
    )
    PARAMETER STYLE JAVA NO SQL 
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureBbb2'
;

DROP PROCEDURE PROCEDURE_TEST_CCC1;
CREATE PROCEDURE PROCEDURE_TEST_CCC1 (
        IN  CCC VARCHAR(20)
       ,IN  DDD NUMERIC
       ,OUT EEE VARCHAR(20)
    )
    PARAMETER STYLE JAVA NO SQL 
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureCcc1'
;

DROP PROCEDURE PROCEDURE_TEST_CCC2;
CREATE PROCEDURE PROCEDURE_TEST_CCC2 (
        OUT CCC VARCHAR(20)
       ,IN  DDD NUMERIC
       ,OUT EEE VARCHAR(20)
    )
    PARAMETER STYLE JAVA NO SQL
    LANGUAGE JAVA
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureCcc2'
;

DROP PROCEDURE PROCEDURE_TEST_DDD1;
CREATE PROCEDURE PROCEDURE_TEST_DDD1 (
        INOUT CCC VARCHAR(20)
    )
    PARAMETER STYLE JAVA NO SQL
    LANGUAGE JAVA 
    EXTERNAL NAME 'org.seasar.dao.impl.Procedures.procedureDdd1'
;

DROP FUNCTION FUNCTION_TEST_MAX;
CREATE FUNCTION FUNCTION_TEST_MAX (
        A DOUBLE
       ,B DOUBLE
    )
    RETURNS DOUBLE
    PARAMETER STYLE JAVA NO SQL
    LANGUAGE JAVA
    EXTERNAL NAME 'java.lang.Math.max'
;

