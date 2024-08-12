#ifndef CPP3_IITERATOR_H
#define CPP3_IITERATOR_H

class IIterator {
  virtual void start() = 0;

  virtual int getElement() = 0;

  virtual void next() = 0;

  virtual bool finish() = 0;
};

#endif  //CPP3_IITERATOR_H
