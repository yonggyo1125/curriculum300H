## execinfo 
- 현재 타임리프 표준 식에서 처리중인 템플릿에 대한 유용한 정보를 제공해 주는 식 객체

```
/*
* ======================================================================
* See javadoc API for class org.thymeleaf.expression.ExecutionInfo
* ======================================================================
*/
/*
* Return the name and mode of the 'leaf' template. This means the template
* from where the events being processed were parsed. So if this piece of
* code is not in the root template "A" but on a fragment being inserted
* into "A" from another template called "B", this will return "B" as a
* name, and B's mode as template mode.
*/
${#execInfo.templateName}
${#execInfo.templateMode}

/*
* Return the name and mode of the 'root' template. This means the template
* that the template engine was originally asked to process. So if this
* piece of code is not in the root template "A" but on a fragment being
* inserted into "A" from another template called "B", this will still
* return "A" and A's template mode.
*/
${#execInfo.processedTemplateName}
${#execInfo.processedTemplateMode}

/*
* Return the stacks (actually, List<String> or List<TemplateMode>) of
* templates being processed. The first element will be the
* 'processedTemplate' (the root one), the last one will be the 'leaf'
* template, and in the middle all the fragments inserted in nested
* manner to reach the leaf from the root will appear.
*/
${#execInfo.templateNames}
${#execInfo.templateModes}

/*
* Return the stack of templates being processed similarly (and in the
* same order) to 'templateNames' and 'templateModes', but returning
* a List<TemplateData> with the full template metadata.
*/
${#execInfo.templateStack}
```

