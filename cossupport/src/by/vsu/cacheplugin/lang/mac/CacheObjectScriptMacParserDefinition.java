package by.vsu.cacheplugin.lang.mac;

import by.vsu.cacheplugin.lang.common.CacheObjectScriptLanguage;
import by.vsu.cacheplugin.lang.mac.parser.CacheObjectScriptMacParser;
import by.vsu.cacheplugin.lang.mac.psi.CacheObjectScriptMacFile;
import by.vsu.cacheplugin.lang.mac.psi.CacheObjectScriptMacTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

public class CacheObjectScriptMacParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(CacheObjectScriptMacTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(Language.<CacheObjectScriptLanguage>findInstance(CacheObjectScriptLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new FlexAdapter(new CacheObjectScriptMacLexer((Reader) null));
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new CacheObjectScriptMacParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new CacheObjectScriptMacFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return CacheObjectScriptMacTypes.Factory.createElement(node);
    }
}