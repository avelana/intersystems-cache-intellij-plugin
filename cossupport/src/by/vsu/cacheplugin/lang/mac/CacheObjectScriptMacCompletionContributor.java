package by.vsu.cacheplugin.lang.mac;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class CacheObjectScriptMacCompletionContributor extends CompletionContributor {
    public CacheObjectScriptMacCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement().withLanguage(CacheObjectScriptLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("BREAK"));
                        resultSet.addElement(LookupElementBuilder.create("CATCH"));
                        resultSet.addElement(LookupElementBuilder.create("CLOSE"));
                        resultSet.addElement(LookupElementBuilder.create("CONTINUE"));
                        resultSet.addElement(LookupElementBuilder.create("DO"));
                        resultSet.addElement(LookupElementBuilder.create("DO WHILE"));
                        resultSet.addElement(LookupElementBuilder.create("ELSE"));
                        resultSet.addElement(LookupElementBuilder.create("ELSEIF"));
                        resultSet.addElement(LookupElementBuilder.create("FOR"));
                        resultSet.addElement(LookupElementBuilder.create("GOTO"));
                        resultSet.addElement(LookupElementBuilder.create("HALT"));
                        resultSet.addElement(LookupElementBuilder.create("HANG"));
                        resultSet.addElement(LookupElementBuilder.create("READ"));
                        resultSet.addElement(LookupElementBuilder.create("IF"));
                        resultSet.addElement(LookupElementBuilder.create("JOB"));
                        resultSet.addElement(LookupElementBuilder.create("KILL"));
                        resultSet.addElement(LookupElementBuilder.create("LOCK"));
                        resultSet.addElement(LookupElementBuilder.create("MERGE"));
                        resultSet.addElement(LookupElementBuilder.create("NEW"));
                        resultSet.addElement(LookupElementBuilder.create("OPEN"));
                        resultSet.addElement(LookupElementBuilder.create("QUIT"));
                        resultSet.addElement(LookupElementBuilder.create("SET"));
                        resultSet.addElement(LookupElementBuilder.create("TCOMMIT"));
                        resultSet.addElement(LookupElementBuilder.create("THROW"));
                        resultSet.addElement(LookupElementBuilder.create("TROLLBACK"));
                        resultSet.addElement(LookupElementBuilder.create("TRY"));
                        resultSet.addElement(LookupElementBuilder.create("TSTART"));
                        resultSet.addElement(LookupElementBuilder.create("USE"));
                        resultSet.addElement(LookupElementBuilder.create("VIEW"));
                        resultSet.addElement(LookupElementBuilder.create("WHILE"));
                        resultSet.addElement(LookupElementBuilder.create("WRITE"));
                        resultSet.addElement(LookupElementBuilder.create("XECUTE"));
                        resultSet.addElement(LookupElementBuilder.create("ZKILL"));
                        resultSet.addElement(LookupElementBuilder.create("ZNSPACE"));
                        resultSet.addElement(LookupElementBuilder.create("ZTRAP"));
                        resultSet.addElement(LookupElementBuilder.create("ZWRITE"));
                        resultSet.addElement(LookupElementBuilder.create("ZZDUMP"));
                    }
                }
        );
    }
}
