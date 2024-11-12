# Code Metrics Review Log

**Reviewer -** Guilherme Simões

**Comments -** 
  - The metrics are not properly explained. By explaining each metric up front, the report would make it clear how these specific measurements are relate to code complexity and potential design issues.
  - The review would benefit from a more thorough breakdown, specifically identifying classes with extreme values in each metric which may suffer from the smells identified in the next point. Specific examples help see which classes may be "bad" and deserve immediate attention.
  - The review mentions unreliability in some metrics for detecting specific code smells but doesn’t suggest why this might be the case or discuss potential limitations in the metrics themselves. This information would help readers understand the context of the metrics and any limitations in using them exclusively to detect issues.
  - While the review discusses correlations between the metrics and code smells, it doesn't delve deeply into why certain metrics indicate particular code smells. Explaining these relationships would strengthen the argument.
