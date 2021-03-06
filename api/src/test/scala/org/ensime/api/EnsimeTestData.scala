// Copyright: 2010 - 2017 https://github.com/ensime/ensime-server/graphs/contributors
// License: http://www.gnu.org/licenses/lgpl-3.0.en.html

package org.ensime.api

import java.io.File

object EnsimeTestData {
  // duplicating utils to minimise dependencies
  private def canon(s: String): RawFile = {
    val file = new File(s)
    val canonised = try file.getCanonicalFile
    catch {
      case t: Throwable => file.getAbsoluteFile
    }
    RawFile(canonised.toPath)
  }

  val typeInfo =
    BasicTypeInfo("type1", DeclaredAs.Method, "FOO.type1", Nil, Nil, None, Nil)

  val interfaceInfo = new InterfaceInfo(typeInfo, Some("DEF"))

  val paramSectionInfo = new ParamSectionInfo(List(("ABC", typeInfo)), false)

  val symFile = canon("/abc")
  val symbolDesignations = SymbolDesignations(
    symFile,
    List(
      SymbolDesignation(7, 9, ObjectSymbol),
      SymbolDesignation(11, 22, TraitSymbol)
    )
  )

  val symbolInfo = new SymbolInfo("name", "localName", None, typeInfo)

  val implicitInfos = List(
    ImplicitConversionInfo(5, 6, symbolInfo),
    ImplicitParamInfo(7, 8, symbolInfo, List(symbolInfo, symbolInfo), true)
  )

  val batchSourceFile = "/abc"

  val packageInfo = new PackageInfo("name", "fullName", Nil)

  val refactorFailure = RefactorFailure(7, "message")

  val file1 = canon("/abc/def")
  val file2 = canon("/test/test/")
  val file3 = canon("/foo/abc")
  val file4 = canon("/foo/def")
  val file5 = canon("/foo/hij")

  val refactorDiffEffect =
    new RefactorDiffEffect(9, RefactorType.AddImport, file2.file.toFile)

  val sourcePos1 = new LineSourcePosition(file1, 57)
  val sourcePos2 = new LineSourcePosition(file1, 59)
  val sourcePos3 = new EmptySourcePosition()
  val sourcePos4 = new OffsetSourcePosition(file1, 456)

  val breakPoint1 = new Breakpoint(RawFile(file1.file), sourcePos1.line)
  val breakPoint2 = new Breakpoint(RawFile(file1.file), sourcePos2.line)

  val breakpointList = BreakpointList(List(breakPoint1), List(breakPoint2))

  val analyzerFile = canon("Analyzer.scala")
  val fooFile      = canon("Foo.scala")

  val abd = canon("/abd")

  val methodSearchRes = MethodSearchResult("abc",
                                           "a",
                                           DeclaredAs.Method,
                                           Some(LineSourcePosition(abd, 10)),
                                           "ownerStr")
  val typeSearchRes = TypeSearchResult("abc",
                                       "a",
                                       DeclaredAs.Trait,
                                       Some(LineSourcePosition(abd, 10)))

  val importSuggestions = new ImportSuggestions(
    List(List(methodSearchRes, typeSearchRes))
  )

  val symbolSearchResults = new SymbolSearchResults(
    List(methodSearchRes, typeSearchRes)
  )

  val fileRange = FileRange("/abc", 7, 9)

  val note1 = new Note("file1", "note1", NoteError, 23, 33, 19, 8)
  val note2 = new Note("file1", "note2", NoteWarn, 23, 33, 19, 8)

  val noteList = NewScalaNotesEvent(isFull = true, List(note1, note2))

  val entityInfo: TypeInfo = new ArrowTypeInfo("Arrow1",
                                               "example.Arrow1",
                                               typeInfo,
                                               List(paramSectionInfo),
                                               Nil)

  val typeParamA =
    BasicTypeInfo("A", DeclaredAs.Nil, "example.Arrow1.A", Nil, Nil, None, Nil)
  val typeParamB =
    BasicTypeInfo("B", DeclaredAs.Nil, "example.Arrow1.B", Nil, Nil, None, Nil)
  val entityInfoTypeParams: TypeInfo = new ArrowTypeInfo(
    "Arrow1",
    "example.Arrow1",
    typeInfo,
    List(paramSectionInfo),
    List(typeParamA, typeParamB)
  )

  val completionInfo = CompletionInfo(Some(typeInfo), "name", 90, Some("BAZ"))

  val completionInfo2 = CompletionInfo(None, "nam", 91, None, true)

  val completionInfoList = List(completionInfo, completionInfo2)

  val sourceFileInfo =
    SourceFileInfo(file1, Some("{/* code here */}"), Some(file2.file.toFile))
  val sourceFileInfo2 = SourceFileInfo(file1)

  val structureView = StructureView(
    List(
      StructureViewMember(
        keyword = "class",
        name = "StructureView",
        position = sourcePos1,
        members = Nil
      ),
      StructureViewMember(
        keyword = "object",
        name = "StructureView",
        position = sourcePos2,
        members = List(
          StructureViewMember(
            keyword = "type",
            name = "BasicType",
            position = sourcePos4,
            members = Nil
          )
        )
      )
    )
  )

  val classInfo =
    ClassInfo(Some("def.foo"), "def$foo", DeclaredAs.Class, Some(sourcePos2))
  val classInfo2    = ClassInfo(None, "java.lang.object", DeclaredAs.Class, None)
  val hierarchyInfo = HierarchyInfo(classInfo2 :: Nil, classInfo :: Nil)
}
