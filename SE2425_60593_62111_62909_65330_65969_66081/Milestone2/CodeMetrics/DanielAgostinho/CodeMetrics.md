# Lorenz-Kidd metrics set (Class level metrics)

1. [NOA: Number of Attributes](#1-number-of-attributes)
2. [NOO: Number of Operations](#2-number-of-operations)
3. [NOAM: Number of Added Methods](#3-number-of-added-methods)

## 1. Number of Attributes:

### Charts:

![NOA_Metrics_values](./img/NOA_Metrics_values.png) <br>
fig1 - NOA metric values

![GodClass_type4_Correlation_chart](./img/GodClass_type4_Correlation_chart.png) <br>
fig2 - NOA GodClass type 4 correlation chart

![TooManyFields_Correlation_chart](./img/TooManyFields_Correlation_chart.png) <br>
fig3 - NOA TooManyFields correlation chart

![NOA_Distribution_chart](./img/NOA_Distribution_chart.png) <br>
fig4 - NOA distribution chart

![NOA_Distribution_chart_table](./img/NOA_Distribution_chart_table.png) <br>
fig5 - NOA distribution chart table

### Potential trouble spots:

- In figure 1 we can observe that the last two (TooManyFields and TooManyMethods) have the highest number and largest 
  spread of outliers, thus we can conclude that this metric is unreliable for identifying those code smells.

### Relation with code smell:

- In figure 1 we can see that `BrainClass` and `FeatureEnvy` are at the bottom of the chart this can indicate that NOA 
  is a good metric for identifying those code smells.
- In figure 2 and 3 we can see that NOA has a value of 100% which indicates that NOA is a good metric to identify
  `GodClass type 4` and `TooManyFields` code smells.
- In figure 4 and 5 we can see that there is a decent percentage of extreme values, most notably in figure 4 we can see
  the classes "ItemTypes" and "BlockTypes" have a disproportional value of Numbers of attributes, which could indicate
  that they have an unusual amount of code smells that can be deduced from NOA, which makes sense as a class with a high
  number of attributes can indicate that it has too many responsibilities and is doing too much 
  (God Class type 4 and BrainClass) or that it is too reliant on other classes (Feature Envy) or that it has too many
  fields/attributes (TooManyFields).


## 2. Number of Operations:

### Charts:

![NOO_Metrics_values](./img/NOO_Metrics_values.png) <br>
fig6 - NOO metric values

### Potential trouble spots:

- In figure 6 we can see that most code smells have a lot of outliers, this can indicate that this metric is unreliable
  for identifying those code smells, except `God Class type 1` and `Feature Envy` which have a low number of outliers
  along with a low dispersion of values.

### Relation with code smell:
- In figure 6 we can see that `God Class type 1` and `Feature Envy` are at the bottom of the chart this can indicate 
  that NOO is a good metric for identifying those code smells, which makes sense as a class with a high number of
  operations can indicate that it has too many responsibilities and is doing too much (God Class type 1) or that it
  is too reliant on other classes (Feature Envy).

## 3. Number of Added Methods:

### Charts:

![NOAM_Metrics_values](./img/NOAM_Metrics_values.png) <br>
fig7 - NOAM metric values

### Potential trouble spots:

- In figure 7 we can see that most code smells have a lot of outliers, this can indicate that this metric is unreliable
  for identifying most code smells, however we can see some exceptions, namely: `Brain Class`, `Feature Envy` and
    `God Class type 1 and 3` which have a low number of outliers.

### Relation with code smell:

- In figure 7 we can see that `BrainClass`, `FeatureEnvy`, `GodClass type 1` and `GodClass type 3` are at the bottom of
  the chart and have little to no outlier this can indicate that NOAM is a good metric for identifying those code smells, 
  which makes sense as a class with a high number of added methods can indicate that it has too many responsibilities and is doing too much 
  (God Class type 1 and 3) or that it is too reliant on other classes (Feature Envy) or that it has too many methods
  (BrainClass).

