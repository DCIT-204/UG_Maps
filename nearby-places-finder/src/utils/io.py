"""io utils"""


def get_file_type() -> str:
    """Get the desired output format from the user."""
    while True:
        output_format = input("Enter the desired output format (csv or text): ").lower()
        if output_format in ["csv", "text"]:
            break
        else:
            print("Invalid format. Please enter 'csv' or 'text'.")
    return output_format
