# MOOD (Metrics for Object-Oriented Design) metrics set (Project level metrics)

1. [MHF: Method Hiding Factor](#1-method-hiding-factor)
2. [PF: Polymorphism Factor](#2-polymorphism-factor)
3. [CF: Coupling Factor](#3-coupling-factor)
4. [AHF: Attribute Hiding Factor](#4-attribute-hiding-factor)

## 1. Method Hiding Factor

MHF is defined as the ratio of the sum of the invisibilities of all methods defined in all classes 
to the total number of methods defined in the system. 
The invisibility of a method is the percentage of the total classes from which this method is not visible.

### Chart

![imagem](https://github.com/user-attachments/assets/62077c24-7615-4e8f-90e9-3475fc9383e4)

### Potential trouble spots

Low MHF could signal that too many methods are publicly accessible,
potentially exposing the internal state or implementation unnecessarily.
High MHF could signal that too few methods are publicly accessible, 
potentially making classes underutilized or redundant.
An MHF value of around 32% lies within the recommended range of 9.5% to 36.9%, which is generally considered an optimal interval according to studies.
This suggests a balanced approach in terms of method visibility and encapsulation within the project.

### Relation with code smells

A low MHF often signals that classes might be exposing too much of their internal logic. 
This can lead to **Inappropriate Intimacy**, where classes become too intertwined and rely on each other’s inner workings,
making future changes difficult and prone to errors. It can also lead to **Feature Envy** since classes with many public methods might tempt
other classes to interact with their internal state or logic excessively.
A high MHF could indicate **Refused Bequest**. 
This happens when a subclass doesn’t fully utilize the functionality of its superclass, suggesting an inappropriate use of inheritance.

## 2. Polymorphism Factor

PF is defined as the quotient between the actual number of different possible polymorphic situations, and the maximum number of possible distinct polymorphic situations for given class.

### Chart

![imagem](https://github.com/user-attachments/assets/8c749974-e17c-403b-870b-a989b36223a1)

### Potential trouble spots

Low PF might indicate an underutilization of polymorphism. This could suggest missed opportunities for flexibility and extensibility, where inheritance and method overriding could be used to avoid duplication or improve design.
High PF might indicate excessive use of polymorphism, where methods are overridden too frequently. This can lead to an overly complex hierarchy that is difficult to maintain and debug.
A Polymorphism Factor (PF) of 55% is well above the recommended range of 1.7% to 15.1%. It means that more than half of the methods across classes are overridden in subclasses. This level of overriding can make the code very complex to understand and maintain.

### Relation with code smells

A high PF might lead to Inappropriate Intimacy, where subclasses know too much about their superclass's internal workings and must override methods just to adapt to the superclass's implementation. With so many overridden methods, it can also result in Shotgun Surgery, where a single change cascades through many parts of the codebase.

## 3. Coupling Factor


### Charts

### Potential trouble spots

### Relation with code smells

## 4. Attribute Hiding Factor

### Charts

### Potential trouble spots

### Relation with code smells
