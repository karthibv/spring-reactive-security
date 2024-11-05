# Function to read password securely
read_password() {
    local prompt=$1
    local password
    read -sp "$prompt" password
    echo $password
}

# Read the first half of the password from the first user
password1=$(read_password "User 1, please enter the first half of the password: ")
echo
# Get the lengths of each password half
length1=${#password1}

echo "first password length "$length1

# Read the second half of the password from the second user
password2=$(read_password "User 2, please enter the second half of the password: ")
echo

length2=${#password2}

echo "second password length "$length2

# Combine the encrypted halves
combined="$password1$password2"
echo
length=${#combined}
echo "total length " $length


# Call the API with the combined encrypted password
curl -X POST https://api.example.com/endpoint \
     -H "Content-Type: application/json" \
     -d "{\"password\":\"$combined\"}"
