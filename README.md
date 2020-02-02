# File-encryption-and-reliablity

Java Project which takes file input and saves it in parts with overhead and provides both Encryption and reliability.



## Working

Project takes file and reads file content in bytes. This file content is encrypted using two different keys and file is stored as six chunk files.
These files can be stored anywhere and to retrieve the file contents any three of these files are required. So in any case system can tolerate 50% percent data loss and can still retrieve file content without data loss.
