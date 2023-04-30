import re

def tex_to_md(tex_string):
    # Replace LaTeX escape characters with their respective markdown escape characters.
    md_string = re.sub(r"\\([#$%&~_^\\{}])", r"\\\1", tex_string)

    # Replace section headers with corresponding markdown headers.
    md_string = re.sub(r"\\section{(.+?)}", r"## \1", md_string)
    md_string = re.sub(r"\\subsection{(.+?)}", r"### \1", md_string)
    md_string = re.sub(r"\\subsubsection{(.+?)}", r"#### \1", md_string)

    # Replace bulleted lists with corresponding markdown list format.
    md_string = re.sub(r"\\begin{itemize}(.*?)\\end{itemize}", 
                       lambda x: "\n" + re.sub(r"\n(.)", r"\n* \1", x.group(1)), 
                       md_string, flags=re.DOTALL)

    # Replace numbered lists with corresponding markdown list format.
    md_string = re.sub(r"\\begin{enumerate}(.*?)\\end{enumerate}", 
                       lambda x: "\n" + re.sub(r"\n(.)", r"\n1. \1", x.group(1)), 
                       md_string, flags=re.DOTALL)

    # Replace bold and italic formatting with corresponding markdown characters.
    md_string = re.sub(r"\\textbf{(.+?)}", r"**\1**", md_string)
    md_string = re.sub(r"\\textit{(.+?)}", r"*\1*", md_string)

    # Replace inline code formatting with corresponding markdown characters.
    md_string = re.sub(r"\\texttt{(.+?)}", r"`\1`", md_string)

    # Replace block code formatting with corresponding markdown characters.
    md_string = re.sub(r"\\begin{verbatim}(.*?)\\end{verbatim}", 
                       lambda x: "\n```\n" + x.group(1) + "\n```\n", 
                       md_string, flags=re.DOTALL)

    return md_string

with open('input.tex', 'r') as file:
    tex_string = file.read()
    md_string = tex_to_md(tex_string)

with open('output.md', 'w') as file:
    file.write(md_string)