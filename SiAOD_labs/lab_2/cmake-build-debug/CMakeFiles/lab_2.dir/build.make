# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\MyProgram\CLion\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\MyProgram\CLion\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\MyProgram\CLion\CLionProject\lab_2

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/lab_2.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/lab_2.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/lab_2.dir/flags.make

CMakeFiles/lab_2.dir/main.cpp.obj: CMakeFiles/lab_2.dir/flags.make
CMakeFiles/lab_2.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/lab_2.dir/main.cpp.obj"
	C:\MyProgram\Mingw\mingw64\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\lab_2.dir\main.cpp.obj -c C:\MyProgram\CLion\CLionProject\lab_2\main.cpp

CMakeFiles/lab_2.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lab_2.dir/main.cpp.i"
	C:\MyProgram\Mingw\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\MyProgram\CLion\CLionProject\lab_2\main.cpp > CMakeFiles\lab_2.dir\main.cpp.i

CMakeFiles/lab_2.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lab_2.dir/main.cpp.s"
	C:\MyProgram\Mingw\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\MyProgram\CLion\CLionProject\lab_2\main.cpp -o CMakeFiles\lab_2.dir\main.cpp.s

CMakeFiles/lab_2.dir/BTree.cpp.obj: CMakeFiles/lab_2.dir/flags.make
CMakeFiles/lab_2.dir/BTree.cpp.obj: ../BTree.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/lab_2.dir/BTree.cpp.obj"
	C:\MyProgram\Mingw\mingw64\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\lab_2.dir\BTree.cpp.obj -c C:\MyProgram\CLion\CLionProject\lab_2\BTree.cpp

CMakeFiles/lab_2.dir/BTree.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lab_2.dir/BTree.cpp.i"
	C:\MyProgram\Mingw\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\MyProgram\CLion\CLionProject\lab_2\BTree.cpp > CMakeFiles\lab_2.dir\BTree.cpp.i

CMakeFiles/lab_2.dir/BTree.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lab_2.dir/BTree.cpp.s"
	C:\MyProgram\Mingw\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\MyProgram\CLion\CLionProject\lab_2\BTree.cpp -o CMakeFiles\lab_2.dir\BTree.cpp.s

# Object files for target lab_2
lab_2_OBJECTS = \
"CMakeFiles/lab_2.dir/main.cpp.obj" \
"CMakeFiles/lab_2.dir/BTree.cpp.obj"

# External object files for target lab_2
lab_2_EXTERNAL_OBJECTS =

lab_2.exe: CMakeFiles/lab_2.dir/main.cpp.obj
lab_2.exe: CMakeFiles/lab_2.dir/BTree.cpp.obj
lab_2.exe: CMakeFiles/lab_2.dir/build.make
lab_2.exe: CMakeFiles/lab_2.dir/linklibs.rsp
lab_2.exe: CMakeFiles/lab_2.dir/objects1.rsp
lab_2.exe: CMakeFiles/lab_2.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable lab_2.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\lab_2.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/lab_2.dir/build: lab_2.exe

.PHONY : CMakeFiles/lab_2.dir/build

CMakeFiles/lab_2.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\lab_2.dir\cmake_clean.cmake
.PHONY : CMakeFiles/lab_2.dir/clean

CMakeFiles/lab_2.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\MyProgram\CLion\CLionProject\lab_2 C:\MyProgram\CLion\CLionProject\lab_2 C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug C:\MyProgram\CLion\CLionProject\lab_2\cmake-build-debug\CMakeFiles\lab_2.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/lab_2.dir/depend

