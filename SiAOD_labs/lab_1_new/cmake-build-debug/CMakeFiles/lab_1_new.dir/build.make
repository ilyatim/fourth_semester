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
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2018.3.4\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\Ilya\CLionProjects\lab_1_new

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\Ilya\CLionProjects\lab_1_new\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/lab_1_new.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/lab_1_new.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/lab_1_new.dir/flags.make

CMakeFiles/lab_1_new.dir/main.cpp.obj: CMakeFiles/lab_1_new.dir/flags.make
CMakeFiles/lab_1_new.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\Ilya\CLionProjects\lab_1_new\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/lab_1_new.dir/main.cpp.obj"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-W\mingw64\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\lab_1_new.dir\main.cpp.obj -c C:\Users\Ilya\CLionProjects\lab_1_new\main.cpp

CMakeFiles/lab_1_new.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/lab_1_new.dir/main.cpp.i"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-W\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\Ilya\CLionProjects\lab_1_new\main.cpp > CMakeFiles\lab_1_new.dir\main.cpp.i

CMakeFiles/lab_1_new.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/lab_1_new.dir/main.cpp.s"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-W\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\Ilya\CLionProjects\lab_1_new\main.cpp -o CMakeFiles\lab_1_new.dir\main.cpp.s

# Object files for target lab_1_new
lab_1_new_OBJECTS = \
"CMakeFiles/lab_1_new.dir/main.cpp.obj"

# External object files for target lab_1_new
lab_1_new_EXTERNAL_OBJECTS =

lab_1_new.exe: CMakeFiles/lab_1_new.dir/main.cpp.obj
lab_1_new.exe: CMakeFiles/lab_1_new.dir/build.make
lab_1_new.exe: CMakeFiles/lab_1_new.dir/linklibs.rsp
lab_1_new.exe: CMakeFiles/lab_1_new.dir/objects1.rsp
lab_1_new.exe: CMakeFiles/lab_1_new.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\Ilya\CLionProjects\lab_1_new\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable lab_1_new.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\lab_1_new.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/lab_1_new.dir/build: lab_1_new.exe

.PHONY : CMakeFiles/lab_1_new.dir/build

CMakeFiles/lab_1_new.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\lab_1_new.dir\cmake_clean.cmake
.PHONY : CMakeFiles/lab_1_new.dir/clean

CMakeFiles/lab_1_new.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\Ilya\CLionProjects\lab_1_new C:\Users\Ilya\CLionProjects\lab_1_new C:\Users\Ilya\CLionProjects\lab_1_new\cmake-build-debug C:\Users\Ilya\CLionProjects\lab_1_new\cmake-build-debug C:\Users\Ilya\CLionProjects\lab_1_new\cmake-build-debug\CMakeFiles\lab_1_new.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/lab_1_new.dir/depend

