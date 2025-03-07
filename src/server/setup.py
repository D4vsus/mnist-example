from setuptools import setup, find_packages

setup(
    name="`MNIST example server`",
    version="1.0.0",
    description="A RESTapi to predict numbers from images",
    long_description=open("../../README.md").read(),
    long_description_content_type="text/markdown",
    author="D4vsus",
    author_email="davsus@protonmail.com",
    url="https://github.com/D4vsus/mnist-example",
    packages=find_packages(),
    install_requires=[
        "Flask==3.1.0",
        "keras==3.7.0",
        "numpy==2.2.3"
    ],
    classifiers=[
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ],
    python_requires='>=3.6',
)