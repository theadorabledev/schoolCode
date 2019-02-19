echo off
set mac=%1
python generate.py %mac% memrise_helper.py
set PROJECT_NAME=temp
set PYTHON_DIR=C:\python27
mkdir build
python -m cython --embed -o memrise_helper.c memrise_helper.py
gcc -c -IC:\Python27\include -o memrise_helper.o memrise_helper.c
gcc -shared -LC:\Python27\libs -o memrise_helper.pyd memrise_helper.o -lpython27
::pyinstaller -y main.spec
pyinstaller -y --add-binary "chromedriver.exe;." main.py
pyinstaller -y --onefile --name memrise-helper --add-binary "chromedriver.exe;." main.py
del *.o
del *.c
del *.spec

::gcc -Os -I %PYTHON_DIR%\include -o memrise_helper.exe temp.c -lpython27 -lm -L %PYTHON_DIR%\libs
::del temp.c
::del temp.py
::python setup.py build_ext --inplace
::pyinstaller mem.py
::copy /Y memrise_helper.exe dist\mem\memrise_helper.exe
::copy /Y chromedriver.exe dist\mem\chromedriver.exe
::mkdir build\data
::copy /Y requirements.txt build\requirements.txt
::copy /Y chromedriver.exe build\chromedriver.exe