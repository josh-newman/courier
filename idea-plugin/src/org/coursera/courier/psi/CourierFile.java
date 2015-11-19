package org.coursera.courier.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.util.PsiTreeUtil;
import org.coursera.courier.CourierFileType;
import org.coursera.courier.CourierLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;
import java.util.Map;

public class CourierFile extends PsiFileBase {
  public CourierFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, CourierLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return CourierFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "Courier File";
  }

  @Override
  public Icon getIcon(int flags) {
    return super.getIcon(flags);
  }

  public CourierTypeNameDeclaration getPrimaryTypeDeclaration() {
    return PsiTreeUtil.findChildOfType(this, CourierTypeNameDeclaration.class);
  }

  public Collection<CourierTypeReference> getTypeReferences() {
    return PsiTreeUtil.findChildrenOfType(this, CourierTypeReference.class);
  }

  public CourierNamespace getNamespace() {
    return PsiTreeUtil.findChildOfType(this, CourierNamespace.class);
  }

  public Collection<CourierImportDeclaration> getImports() {
    return PsiTreeUtil.findChildrenOfType(getContainingFile(), CourierImportDeclaration.class);
  }

  public TypeName lookupImport(String name) {
    Collection<CourierImportDeclaration> importDecls = getImports();
    for (CourierImportDeclaration importDecl : importDecls) {
      String importName = importDecl.getName();
      if (importName != null && importName.equals(name)) {
        return importDecl.getFullname();
      }
    }
    return null;
  }
}
