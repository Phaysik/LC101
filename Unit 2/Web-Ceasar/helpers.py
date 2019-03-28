def alphabet_position(letter):
    if (ord(letter) > 64 and ord(letter) < 91):  # Uppercase
        return (ord(letter) - 65) % 26
    elif (ord(letter) > 96 and ord(letter) < 123):  # Lowercase
        return (ord(letter) - 97) % 26
    else:
        return letter


def rotate_character(char, rot):
    if (rot < 0):  # If value is below zero
        return rotate_character(char, rot + 26)
    else:
        if(char.isalpha()):
            if (char.isupper()):    # Uppercase
                return chr((alphabet_position(char) + rot) % 26 + 65)
            else:   # Lowercase
                return chr((alphabet_position(char) + rot) % 26 + 97)
        else:
            return char
