## About
Spring batch demos.


# Features

## Managing Jobs
But mission-critical processes require a more robust approach. The ability to keep the state of a job for reexecution, 
maintaining data integrity when a job fails through transaction management and saving performance metrics of past job 
executions for trending, are features that you expect in an enterprise batch system. These features are included in 
Spring Batch, and most of them are turned on by default; they require only minimal tweaking for performance and 
requirements as you develop your process.

## Local and Remote Parallelization
the scale of batch jobs and the need to be able to scale them is vital to any enterprise batch solution. Spring Batch 
provides the ability to approach this in a number of different ways. From a simple thread-based implementation, where 
each commit interval is processed in its own thread of a thread pool; to running full steps in parallel; to configuring
 a grid of workers that are fed units of work from a remote master via partitioning; Spring Batch provides a collection 
 of different options, including parallel chunk/step processing, remote chunk processing, and partitioning.
 
## Standardizing I/O
Reading in from flat files with complex formats, XML files (XML is streamed, never loaded as a whole), or even a database, 
or writing to files or XML, can be done with only XML configuration. The ability to abstract things like file and database 
input and output from your code is an attribute of the maintainability of jobs written in Spring Batch.

## Reference
- http://www.javacodegeeks.com/2015/03/spring-batch-tutorial.html
- https://github.com/codecentric/spring-batch-javaconfig/
- http://www.codingpedia.org/ama/spring-batch-tutorial-with-spring-boot-and-java-configuration/
- http://docs.spring.io/spring-batch/
- https://www.youtube.com/watch?v=lHCPppMlylY
- http://websystique.com/spring-batch-tutorial/

- Safari:
-- http://my.safaribooksonline.com/book/-/9781430234524
-- http://my.safaribooksonline.com/book/programming/java/9781935182955
