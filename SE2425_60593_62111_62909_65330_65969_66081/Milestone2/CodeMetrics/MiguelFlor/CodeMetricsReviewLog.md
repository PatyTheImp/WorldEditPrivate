# Code Metrics Review Log

**Reviewer -** Daniel Agostinho

**Comments -**
## 1. Number of Methods
The `NOM` metric assessment seems to be fully correct and well explained, there is no correction I would make to this.
It describes the metric well and gives the reader a good understanding of what the metric is and how it can be used.

## 2. Number of Attributes and Methods
The `SIZE2` metric although it doesn't seem to be a good metric to evaluate the codebase, it is well explained and 
analyzed, the only problem I can find with your assessment is that saying a `high size2` is a good metric for finding 
`God Classes`, but it only has a value of 28% percent, which is not that high (even if it is the highest compared to 
other metrics), so it might not be the best metric to find God Classes, this isn't really a problem, 
but it is something to consider.

## 3. Data Abstraction Coupling
The `DAC` seems to be a good metric to evaluate the codebase, and it is well explained and analyzed,
however, high coupling does have `3 outliers`, which may indicate that there are some classes that are too dependent on 
other abstract classes. This may `hinder the flexibility` of the codebase, making it harder to maintain and extend.
Although it might not be a problem, for correction’s sake it `should be removed in trouble spots` where u mention it has 
almost no outliers.
As a small sidenote on line 63 of the file "AS" should be written as "As".

